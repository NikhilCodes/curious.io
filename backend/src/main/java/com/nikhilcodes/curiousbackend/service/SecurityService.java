package com.nikhilcodes.curiousbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityService extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public SecurityService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
//        String hash1 = "$2a$10$0gr7INy2Vh9ElLle3mExy.Z1wKpkkwbRme.OmSossvYn1fr4lPUhW";
//        String hash2 = "$2a$10$YVtlaS89K0KVenSQOwQsm.ZTkZ4lLUsSw99AyhLnOYkP3TgqS9Y9K";
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
//                .withUser("admin@nixel.com")
//                .password(hash1)
//                .roles("USER")
//                .and()
//                .withUser("nikhil.nixel@gmail.com")
//                .password(hash2)
//                .roles("ADMIN");

        auth.jdbcAuthentication().passwordEncoder(passwordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, enabled FROM users_db WHERE email=?") // NOTE: We are considering email as username from login-form
                .authoritiesByUsernameQuery("SELECT email, role FROM users_db WHERE email=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityContext loginWithEmailAndPassword(String email, String password) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(email, password);
        try {
            Authentication authentication = this.authenticationManagerBean().authenticate(authReq);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            return securityContext;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
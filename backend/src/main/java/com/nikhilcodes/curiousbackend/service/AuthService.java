package com.nikhilcodes.curiousbackend.service;

import com.nikhilcodes.curiousbackend.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.cors.CorsUtils;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AuthService extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final AuthDao authDao;

    @Autowired
    public AuthService(@Qualifier("user-dao")AuthDao authDao, DataSource dataSource) {
        this.authDao = authDao;
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, enabled FROM users_db WHERE email=?") // NOTE: We are considering email as username from login-form
                .authoritiesByUsernameQuery("SELECT email, role FROM users_db WHERE email=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .antMatcher("/api/**")
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest)
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

    public SecurityContext createUser(String username, String email, String passwordPlain) {
        String passwordHashed = passwordEncoder().encode(passwordPlain);
        authDao.createUser(username, email, passwordHashed);
        return loginWithEmailAndPassword(email, passwordPlain);
    }
}
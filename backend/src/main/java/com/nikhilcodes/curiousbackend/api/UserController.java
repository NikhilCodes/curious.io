package com.nikhilcodes.curiousbackend.api;

import com.nikhilcodes.curiousbackend.model.UserModel;
import com.nikhilcodes.curiousbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.desktop.SystemEventListener;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Content-Type", "Access-Control-Allow-Origin", "Accept", "X-Requested-With", "remember-me"}, allowCredentials = "true")
@RestController
@RequestMapping("/api/user")
public class UserController {
    final AuthService authService;

    @Autowired
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(path = "/")
    public Optional<UserModel> getCurrentUser(@CurrentSecurityContext(expression = "authentication.name") String email) {
        return authService.getUserByEmail(email);
    }
}

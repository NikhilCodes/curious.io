package com.nikhilcodes.curiousbackend.api;

import com.nikhilcodes.curiousbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthController {
    final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public void register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SecurityContext securityContext = authService.createUser(username, email, password);
        if(securityContext != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        }
    }

    @PostMapping(path = "/login")
    public void login(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SecurityContext securityContext = authService.loginWithEmailAndPassword(email, password);
        HttpSession session = request.getSession(true);
        if(securityContext != null) {
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        } else {
            session.invalidate();
        }
    }

    @GetMapping(path = "/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if (session != null) {
            session.invalidate();
        }

        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
    }

    @PostMapping(path="/authenticated")
    public boolean isAuthenticated(HttpServletRequest request) {
        return request.isRequestedSessionIdValid();
    }
}

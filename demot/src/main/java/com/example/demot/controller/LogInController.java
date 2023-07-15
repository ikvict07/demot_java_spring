package com.example.demot.controller;

import ch.qos.logback.core.model.Model;
import com.example.demot.model.CustomUser;
import com.example.demot.model.Task;
import com.example.demot.repository.CustomUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LogInController {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/sign-up")
    public ResponseEntity<CustomUser> createUser(@RequestBody CustomUser user) {
        CustomUser newUser = new UserServiceImpl().createUser(user);
        return new ResponseEntity<>(customUserRepository.save(newUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        Model model,
                        HttpServletRequest request) {
        try {
            request.login(login, password); // Authenticate user using Spring Security

            // User is authenticated successfully
            return "redirect:/tasks"; // Redirect to the home page after successful login
        } catch (ServletException e) {
            return "redirect:/sign-up";
        }
    }
}

package com.example.demot.controller;


import com.example.demot.model.CustomUser;
import com.example.demot.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private CustomUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomUser createUser(CustomUser customUser) {
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));

        return userRepository.save(customUser);
    }

    public CustomUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        return userRepository.findByLogin(principal.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
    }


}
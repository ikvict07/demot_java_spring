package com.example.demot.controller;

import com.example.demot.model.CustomUser;
import com.example.demot.model.Task;
import com.example.demot.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {
    @Autowired
    CustomUserRepository customUserRepository;
    @PostMapping("/sign-up")
    public ResponseEntity<CustomUser> create(@RequestBody CustomUser user){
        CustomUser newUser = new UserServiceImpl().CreateUser(user, customUserRepository);
        return new ResponseEntity<>(customUserRepository.save(user), HttpStatus.CREATED);
    }
}

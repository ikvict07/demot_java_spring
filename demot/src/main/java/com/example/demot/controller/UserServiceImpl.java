package com.example.demot.controller;


import com.example.demot.model.CustomUser;
import com.example.demot.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    public CustomUser CreateUser(CustomUser user, CustomUserRepository customUserRepository) {
        return customUserRepository.save(user);
    }
}
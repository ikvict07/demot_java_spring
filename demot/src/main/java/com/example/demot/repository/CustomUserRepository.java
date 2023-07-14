package com.example.demot.repository;

import com.example.demot.model.CustomUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {
}

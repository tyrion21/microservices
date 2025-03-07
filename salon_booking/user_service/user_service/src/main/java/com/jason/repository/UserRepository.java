package com.jason.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jason.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    
} 
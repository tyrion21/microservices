package com.jason.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jason.model.User;
import com.jason.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Long id) throws RuntimeException {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isPresent()) {
            return otp.get();
        }
        throw new RuntimeException("User not found");

    }
    
    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/api/users/{userId}")
    public User updateUser(@PathVariable("userId") Long id, @RequestBody User user) throws RuntimeException {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User existingUser = otp.get();

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }   

    @DeleteMapping("/api/users/{userId}")
    public String deleteUser(@PathVariable("userId") Long id) throws RuntimeException {

        Optional<User> otp = userRepository.findById(id);
        if(otp.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }

        userRepository.deleteById(otp.get().getId());
        return "User deleted successfully";
    }

}

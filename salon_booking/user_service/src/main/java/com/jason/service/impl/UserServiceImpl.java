package com.jason.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.jason.exception.UserException;
import com.jason.model.User;
import com.jason.repository.UserRepository;
import com.jason.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
         Optional<User> otp = userRepository.findById(id);
        if (otp.isPresent()) {
            return otp.get();
        }
        throw new UserException("User not found");
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) throws UserException {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isEmpty()) {
            throw new UserException("User not found");
        }
        User existingUser = otp.get();

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setUsername(user.getUsername());       

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) throws RuntimeException {
        Optional<User> otp = userRepository.findById(id);
        if (otp.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }

        userRepository.deleteById(otp.get().getId());
    }
    
}

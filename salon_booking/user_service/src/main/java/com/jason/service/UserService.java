package com.jason.service;

import java.util.List;

import com.jason.exception.UserException;
import com.jason.model.User;

public interface UserService {
    
    User createUser(User user);

    User getUserById(Long id) throws UserException;

    List<User> getUsers();

    User updateUser(Long id, User user) throws UserException;

    void deleteUser(Long id);

}

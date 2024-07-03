package com.example.retailstore.services;

import com.example.retailstore.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User user);

    void deleteUser(Long id);

    User getUserByUsername(String username);

    boolean validateUser(String username, String password);
}

package com.nsmstravels.nsms_backend.services;

import com.nsmstravels.nsms_backend.model.User;
import com.nsmstravels.nsms_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid Password!");
        }

        return user;
    }
}
package com.nsmstravels.nsms_backend.controller;

import com.nsmstravels.nsms_backend.model.User;
import com.nsmstravels.nsms_backend.repository.UserRepository;
import com.nsmstravels.nsms_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser (@RequestBody User user)
    {
        return  userRepository.save(user);
    }

    @PostMapping("/login")
    public User login (@RequestBody Map<String,String> Credentials)
    {
        String email= Credentials.get("email");
        String password = Credentials.get("password");

        return userService.loginUser(email,password);
    }
}

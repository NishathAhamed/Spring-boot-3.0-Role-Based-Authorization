package com.example.RoleBasedAuthorization.controller;

import com.example.RoleBasedAuthorization.Entities.User;
import com.example.RoleBasedAuthorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("user/register")
    public void register(@RequestBody User user){
        userRepository.save(user);
    }
}

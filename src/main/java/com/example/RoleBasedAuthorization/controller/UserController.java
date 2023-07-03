package com.example.RoleBasedAuthorization.controller;

import com.example.RoleBasedAuthorization.Entities.User;
import com.example.RoleBasedAuthorization.repository.UserRepository;
import com.example.RoleBasedAuthorization.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUser();
    }
    @PostMapping("/admin/setRole/{role}/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String setRole(@PathVariable String role,@PathVariable String username){
        System.out.println("nishath");
        userService.setRole(role,username);
        return "Role is set";
    }




}

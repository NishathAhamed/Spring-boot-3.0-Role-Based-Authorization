package com.example.RoleBasedAuthorization.service;


import com.example.RoleBasedAuthorization.Entities.Roles;
import com.example.RoleBasedAuthorization.Entities.User;
import com.example.RoleBasedAuthorization.repository.RoleRepository;
import com.example.RoleBasedAuthorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public void setRole(String role,String username) {
        User user=userRepository.findByUserName(username).get();
        Roles roles=roleRepository.findByName(role);
        user.getRoles().add(roles);
        userRepository.save(user);
    }

    public void initRolesAndUser() {


        Roles role1;
        role1 = new Roles();
        role1.setName("ROLE_ADMIN");
        var user= User.builder()
                .userName("Janith")
                .password(passwordEncoder.encode("pwd1"))
                .build();
        Set<Roles> roles = new HashSet<>();
        roles.add(role1);
        user.setRoles(roles);
        userRepository.save(user);
        roleRepository.save(role1);

        Roles role2;
        role2 = new Roles();
        role2.setName("ROLE_USER");
        roleRepository.save(role2);



    }
}

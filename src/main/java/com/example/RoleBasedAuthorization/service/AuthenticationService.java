package com.example.RoleBasedAuthorization.service;

import com.example.RoleBasedAuthorization.Entities.Roles;
import com.example.RoleBasedAuthorization.Entities.User;
import com.example.RoleBasedAuthorization.config.JwtService;
import com.example.RoleBasedAuthorization.dto.AuthRequestDto;
import com.example.RoleBasedAuthorization.dto.AuthResponseDto;
import com.example.RoleBasedAuthorization.dto.UserRequestDto;
import com.example.RoleBasedAuthorization.repository.RoleRepository;
import com.example.RoleBasedAuthorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponseDto register(UserRequestDto userRequestDto){
        var user= User.builder()
                .userName(userRequestDto.getUserName())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        Set<Roles> roles = new HashSet<>();

        Roles role1=roleRepository.findByName("ROLE_USER");

        roles.add(role1);

        user.setRoles(roles);

        userRepository.save(user);
        String token=jwtService.generateToken(user);

        return AuthResponseDto.builder().token(token).build();
    }


    public AuthResponseDto authenticate(AuthRequestDto authRequestDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getUserName(),
                        authRequestDto.getPassword()
                )
        );
        var user=userRepository.findByUserName(authRequestDto.getUserName()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthResponseDto.builder().token(jwtToken).build();
    }
}

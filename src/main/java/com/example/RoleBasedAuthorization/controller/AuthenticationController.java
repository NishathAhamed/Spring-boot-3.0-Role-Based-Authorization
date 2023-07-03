package com.example.RoleBasedAuthorization.controller;


import com.example.RoleBasedAuthorization.Entities.User;
import com.example.RoleBasedAuthorization.dto.AuthRequestDto;
import com.example.RoleBasedAuthorization.dto.AuthResponseDto;
import com.example.RoleBasedAuthorization.dto.UserRequestDto;
import com.example.RoleBasedAuthorization.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {



    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/auth/reg")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(authenticationService.register(userRequestDto));
    }

    @PostMapping("/auth/authenticate")
    public ResponseEntity<AuthResponseDto> authenticate(@RequestBody AuthRequestDto authRequestDto){
        return ResponseEntity.ok(authenticationService.authenticate(authRequestDto));
    }




}

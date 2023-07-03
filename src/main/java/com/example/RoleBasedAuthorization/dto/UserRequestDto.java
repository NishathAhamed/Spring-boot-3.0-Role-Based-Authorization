package com.example.RoleBasedAuthorization.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRequestDto {

    private String userName;
    private String password;
}

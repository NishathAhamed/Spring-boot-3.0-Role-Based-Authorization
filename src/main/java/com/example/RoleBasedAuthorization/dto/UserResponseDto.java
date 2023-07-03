package com.example.RoleBasedAuthorization.dto;


import com.example.RoleBasedAuthorization.Entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Integer userId;
    private String userName;
    private String password;
    private Set<Roles> roles;


}

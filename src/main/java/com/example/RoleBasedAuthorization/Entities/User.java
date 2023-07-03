package com.example.RoleBasedAuthorization.Entities;

import com.example.RoleBasedAuthorization.repository.UserRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_tbl")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name ="user_fk")},
            inverseJoinColumns = {@JoinColumn(name = "role_fk")}
    )
    private Set<Roles> roles;
}

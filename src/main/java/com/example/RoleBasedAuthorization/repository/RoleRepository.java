package com.example.RoleBasedAuthorization.repository;

import com.example.RoleBasedAuthorization.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Roles,Integer> {
    Roles findByName(String role_user);
}

package com.example.RoleBasedAuthorization.repository;

import com.example.RoleBasedAuthorization.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}

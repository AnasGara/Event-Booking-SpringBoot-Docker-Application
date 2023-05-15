package com.example.eventbooking.Repositories;

import com.example.eventbooking.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);
    User findByEmail(String email);
}
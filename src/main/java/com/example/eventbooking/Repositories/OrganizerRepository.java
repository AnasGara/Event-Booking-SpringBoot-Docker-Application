package com.example.eventbooking.Repositories;

import com.example.eventbooking.Models.Organizer;
import com.example.eventbooking.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    boolean existsByEmail(String email);
    Organizer findByEmail(String email);

}
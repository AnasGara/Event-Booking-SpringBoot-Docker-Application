package com.example.eventbooking.Repositories;

import com.example.eventbooking.Models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue, Long> {

    Venue findTopByOrderByIdDesc();
}
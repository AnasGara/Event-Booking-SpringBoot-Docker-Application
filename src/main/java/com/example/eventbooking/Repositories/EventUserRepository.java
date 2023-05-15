package com.example.eventbooking.Repositories;

import com.example.eventbooking.Models.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventUserRepository extends JpaRepository<EventUser, Long> {

    EventUser findEventUserByEventIdAndUserId(Long idEvent,Long idUser);

    List<EventUser> findEventUserByUserId(Long id);
}
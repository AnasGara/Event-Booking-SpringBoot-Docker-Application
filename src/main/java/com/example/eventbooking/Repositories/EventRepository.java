package com.example.eventbooking.Repositories;

import com.example.eventbooking.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findTopByOrderByIdDesc();


    List<Event> findEventByOrganizerId(Long id);
//    List<Event>findEventByEventUsers(Long id);
}
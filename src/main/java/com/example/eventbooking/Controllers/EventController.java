package com.example.eventbooking.Controllers;


import com.example.eventbooking.Models.Event;
import com.example.eventbooking.Models.EventUser;
import com.example.eventbooking.Models.Organizer;
import com.example.eventbooking.Models.Venue;
import com.example.eventbooking.Repositories.EventRepository;
import com.example.eventbooking.Repositories.EventUserRepository;
import com.example.eventbooking.Repositories.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired

    private EventRepository eventRepository;


    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private EventUserRepository eventUserRepository;

    @GetMapping
    private List<Event>listEvents(){
        return eventRepository.findAll();
    }


    @PostMapping(value = "{id}")
    private Event addEvent(@RequestBody Event event,@PathVariable Long id){
        Organizer organizer=organizerRepository.findById(id).orElse(null);
        event.setOrganizer(organizer);
        eventRepository.save(event);
        return event;
    }

    @GetMapping(value = "{id}")
    private Optional<Event> getEventById(@PathVariable Long id){
        return eventRepository.findById(id);
    }

    @DeleteMapping(value = "{id}")
    private void deleteEvent(@PathVariable Long id){
        eventRepository.deleteById(id);
    }


    @PutMapping(value = "{id}")
    private ResponseEntity<?>updateEvent(@PathVariable Long id,@RequestBody Event event){
        if(!eventRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        event.setId(id);
        eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }
    @GetMapping("/last")
    private Event getLastEvent() {
        return eventRepository.findTopByOrderByIdDesc();
    }


    @GetMapping(value = "getbyorganizer/{id}")
    private List<Event> getEventsByOrganizerId(@PathVariable Long id){
        return eventRepository.findEventByOrganizerId(id);
    }

    @GetMapping("/bookedEvent/{id}")
    private List<Event>BookedEventsByUserId(@PathVariable Long id){
        List<EventUser>EventUsers=eventUserRepository.findEventUserByUserId(id);
        List<Event>Events=new ArrayList<>();
        for(EventUser eventuser : EventUsers){
            Events.add(eventRepository.findById(eventuser.getEvent().getId()).orElse(null));
        }
        return Events;
    }

}

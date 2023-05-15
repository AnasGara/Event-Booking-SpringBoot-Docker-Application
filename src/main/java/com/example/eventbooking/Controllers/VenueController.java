package com.example.eventbooking.Controllers;


import com.example.eventbooking.Models.Event;
import com.example.eventbooking.Models.Venue;
import com.example.eventbooking.Repositories.EventRepository;
import com.example.eventbooking.Repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/venue")
public class VenueController {


    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private EventRepository eventRepository;



    @GetMapping
    private List<Venue>listVenues(){
        return venueRepository.findAll();
    }

    @GetMapping(value = "{id}")
    private Optional<Venue> getVenueById(@PathVariable Long id){
        return venueRepository.findById(id);
    }

    @PostMapping(value = "{id}")
    private Venue addVenue(@RequestBody Venue venue,@PathVariable Long id){
        Event event=eventRepository.findById(id).orElse(null);
        venue.setEvent(event);
        venueRepository.save(venue);
        return venue;
    }

    @DeleteMapping(value = "{id}")
    private String deleteVenue(@PathVariable Long id){
        venueRepository.deleteById(id);
        return "deletion success";
    }

    @PutMapping(value = "{id}")
    private ResponseEntity<?>updateVenue(@PathVariable Long id,@RequestBody Venue venue){
        if(!venueRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        venue.setId(id);
        venueRepository.save(venue);
        return ResponseEntity.status(HttpStatus.OK).body(venue);
    }
    @GetMapping("/last")
    private Venue getLastVenue() {
        return venueRepository.findTopByOrderByIdDesc();
    }
}

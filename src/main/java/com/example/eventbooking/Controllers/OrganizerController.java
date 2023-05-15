package com.example.eventbooking.Controllers;


import com.example.eventbooking.Models.Organizer;
import com.example.eventbooking.Repositories.OrganizerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@CrossOrigin("*")

@RequestMapping(value = "/organizer")
public class OrganizerController {

    @Autowired
    private OrganizerRepository organizerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @GetMapping
    private List<Organizer>listOrganizers(){
        return organizerRepository.findAll();
    }

    @GetMapping(value = "{id}")
    private Optional<Organizer> getOrganizerById(@PathVariable Long id){
        return organizerRepository.findById(id);
    }

    @GetMapping(value = "getbyemail")
    private Organizer getOrganizerByEmail(@RequestParam String email){
        return organizerRepository.findByEmail(email);
    }
    @PostMapping(path = "register")
    private ResponseEntity<?> addOrganizer(@RequestBody Organizer organizer){
        if(organizerRepository.existsByEmail(organizer.getEmail())){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        organizer.setPassword(this.bCryptPasswordEncoder.encode(organizer.getPassword()));
        organizerRepository.save(organizer);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizer);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Organizer organizer) {

        HashMap<String, Object> response = new HashMap<>();

        Organizer organizerFromDB = organizerRepository.findByEmail(organizer.getEmail());

        if (organizerFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else {

            Boolean compare = this.bCryptPasswordEncoder.matches(organizer.getPassword(), organizerFromDB.getPassword());

            if (!compare) {
                response.put("message", "Wrong password !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            else {
                String token = Jwts.builder()
                        .claim("data", organizerFromDB)
                        .claim("role","organizer")
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

    }

    @DeleteMapping(value = "{id}")
    private String deleteOrganizer(@PathVariable Long id){
        organizerRepository.deleteById(id);
        return "deletion Success";
    }

    @PutMapping(value = "{id}")
    private ResponseEntity<?>updateOrganizer(@PathVariable Long id,@RequestBody Organizer organizer){
        if(!organizerRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        organizer.setId(id);
        organizerRepository.save(organizer);
        return ResponseEntity.status(HttpStatus.OK).body(organizer);
    }
}

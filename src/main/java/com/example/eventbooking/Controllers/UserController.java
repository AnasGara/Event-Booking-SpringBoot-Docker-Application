package com.example.eventbooking.Controllers;


import com.example.eventbooking.Models.User;
import com.example.eventbooking.Repositories.UserRepository;
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
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @GetMapping
    private List<User>UsersList(){
        return userRepository.findAll();
    }
    @PostMapping(path = "register")
    private ResponseEntity<?> addUser(@RequestBody User user){
        if(userRepository.existsByEmail(user.getEmail())){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user) {

        HashMap<String, Object> response = new HashMap<>();
        User userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB == null) {
            response.put("message", "user not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        else {

            Boolean compare = this.bCryptPasswordEncoder.matches(user.getPassword(), userFromDB.getPassword());

            if (!compare) {
                response.put("message", "Wrong password !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            else {
                String token = Jwts.builder()
                        .claim("data", userFromDB)
//                        .claim("role","user")
                        .signWith(SignatureAlgorithm.HS256, "SECRET")
                        .compact();
                response.put("token", token);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    }


    @GetMapping(value = "{id}")
    private Optional<User> getUserById(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @DeleteMapping(value = "{id}")
    private String deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return "Deletion success";
    }


    @PutMapping(value = "{id}")
    private ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user){
        if(!userRepository.existsById(id)){
            return new ResponseEntity<Void>(HttpStatus.FOUND);
        }
        user.setId(id);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}

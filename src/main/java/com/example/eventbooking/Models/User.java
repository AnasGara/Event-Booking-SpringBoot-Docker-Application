package com.example.eventbooking.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;
    @Lob
    private String photo;


//    @ManyToMany(mappedBy = "users")
//    List<Event>events;

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private List<EventUser> eventUsers;


}
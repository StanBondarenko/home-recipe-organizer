package com.stanbond.homerecipeorganizer.security.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false, name= "first_name")
    private String firstName;
    @Column(nullable = false, name="last_name")
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, name="password_hash")
    private String password;
    @Column(name="day_of_birth")
    private LocalDate birth;
    @Column(nullable = false)
    private LocalDateTime createdAt;
}

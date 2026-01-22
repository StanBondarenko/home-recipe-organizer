package com.stanbond.homerecipeorganizer.security.auth;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequest {
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birth;
}

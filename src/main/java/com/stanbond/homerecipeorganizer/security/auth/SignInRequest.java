package com.stanbond.homerecipeorganizer.security.auth;

import lombok.Data;

@Data
public class SignInRequest {
    private String identifier;
    private String password;
}

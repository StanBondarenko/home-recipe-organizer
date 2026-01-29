package com.stanbond.homerecipeorganizer.security.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/secured")
public class UserController {
    private final UserService service;
    public UserController (UserService service){
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<User> getUserByLogin(Principal principal){
        return ResponseEntity.ok(service.getUserByLogin(principal));
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('GOD')")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email){
        return ResponseEntity.ok(service.getUserByEmail(email));
    }
    @GetMapping("/user/all")
    @PreAuthorize("hasRole('GOD')")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(service.getAllUsers());
    }
}

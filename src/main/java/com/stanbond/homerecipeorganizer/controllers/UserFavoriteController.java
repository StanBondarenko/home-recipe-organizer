package com.stanbond.homerecipeorganizer.controllers;
import com.stanbond.homerecipeorganizer.DAO.entites.UserFavorite;
import com.stanbond.homerecipeorganizer.service.UserFavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/my/favorites")
@PreAuthorize("isAuthenticated()")
public class UserFavoriteController {

    private final UserFavoriteService service;

    public UserFavoriteController(UserFavoriteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserFavorite>> getAll(Principal principal) {
        return ResponseEntity.ok(service.getAll(principal));
    }
    @GetMapping("/{recId}")
    public ResponseEntity<List<UserFavorite>> getByRecId(Principal principal,@PathVariable long recId){
        return ResponseEntity.ok(service.getByUserIdAndRecId(principal,recId));
    }

    @PostMapping("/{recId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@PathVariable long recId, Principal principal) {
        service.addByEmail(principal, recId);
    }

    @DeleteMapping("/{recId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable long recId, Principal principal) {
        service.removeByEmail(principal, recId);
    }
}
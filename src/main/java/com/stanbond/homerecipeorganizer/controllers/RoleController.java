package com.stanbond.homerecipeorganizer.controllers;

import com.stanbond.homerecipeorganizer.DAO.entites.Role;
import com.stanbond.homerecipeorganizer.repositores.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleRepository repo;
    public RoleController(RoleRepository repo){
        this.repo = repo;
    }
    @GetMapping()
    public ResponseEntity<List<Role>> getAllRole(){
        return ResponseEntity.ok(repo.findAll());
    }
}

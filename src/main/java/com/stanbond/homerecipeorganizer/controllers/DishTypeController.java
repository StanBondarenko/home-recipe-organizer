package com.stanbond.homerecipeorganizer.controllers;

import com.stanbond.homerecipeorganizer.DAO.entites.DishType;
import com.stanbond.homerecipeorganizer.repositores.DishTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/type")
public class DishTypeController {
    private final DishTypeRepository repo;
    public DishTypeController(DishTypeRepository repo){
        this.repo = repo;
    }
    @GetMapping()
        public ResponseEntity<List<DishType>> getAllType(){
             return ResponseEntity.ok(repo.findAll());
    }
}

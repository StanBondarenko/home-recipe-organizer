package com.stanbond.homerecipeorganizer.controllers;

import com.stanbond.homerecipeorganizer.DAO.entites.Ingredient;
import com.stanbond.homerecipeorganizer.DTO.ingredient.CreateIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.ingredient.UpdateIngredientDto;
import com.stanbond.homerecipeorganizer.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/ing")
public class IngredientController {
    private final IngredientService service;
    public IngredientController(IngredientService service){this.service=service;}
    @GetMapping("")
    public ResponseEntity<List<Ingredient>> get(@RequestParam(name = "name", required = false, defaultValue = "") String name){
        if(name.isEmpty()){
            return ResponseEntity.ok(service.getAllIngrediets());
        }else {
            name =name.trim();
            return ResponseEntity.ok(List.of(service.getIngByName(name)));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@Valid @PathVariable long id){
        return ResponseEntity.ok(service.getIngById(id));
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @PostMapping
    public ResponseEntity<Ingredient> create(@Valid @RequestBody CreateIngredientDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createIng(dto.name().trim()));
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable int id,@Valid @RequestBody UpdateIngredientDto dto){
        Ingredient updated = service.updateIng(id, dto);
        return ResponseEntity.ok(updated);
    }
}

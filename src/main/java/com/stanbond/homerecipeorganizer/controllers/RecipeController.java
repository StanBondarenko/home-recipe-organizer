package com.stanbond.homerecipeorganizer.controllers;

import com.stanbond.homerecipeorganizer.DAO.entites.Recipe;
import com.stanbond.homerecipeorganizer.DTO.recipe.CreateRecipeDto;
import com.stanbond.homerecipeorganizer.DTO.recipe.UpdateRecipeDto;
import com.stanbond.homerecipeorganizer.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rec")
public class RecipeController {
    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Recipe>> find(
            @RequestParam(name="name",defaultValue = "") String name,
            @RequestParam(name="id",defaultValue = "0") long id,
            @RequestParam(name="type",defaultValue = "") String typeName) {
        return ResponseEntity.ok(service.find(name,id,typeName));
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @PostMapping
    public ResponseEntity<Recipe> create(@Valid @RequestBody CreateRecipeDto dto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable int id, @Valid @RequestBody UpdateRecipeDto dto){
        Recipe updated = service.update(id,dto);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        service.deleteRecipe(id);
    }
}

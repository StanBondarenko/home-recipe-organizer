package com.stanbond.homerecipeorganizer.controllers;
import com.stanbond.homerecipeorganizer.DAO.entites.RecipeStep;
import com.stanbond.homerecipeorganizer.DTO.recipeStep.CreateRecipeStepDto;
import com.stanbond.homerecipeorganizer.DTO.recipeStep.UpdateRecipeStepDto;
import com.stanbond.homerecipeorganizer.service.RecipeStepService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes/{recId}/steps")
public class RecipeStepController {

    private final RecipeStepService service;

    public RecipeStepController(RecipeStepService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RecipeStep>> getAll(@PathVariable long recId) {
        return ResponseEntity.ok(service.getSteps(recId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @PostMapping
    public ResponseEntity<Long> create(@PathVariable long recId,
                                       @Valid @RequestBody CreateRecipeStepDto dto) {
        long stepId = service.create(recId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(stepId);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @PatchMapping("/{stepId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long recId,
                       @PathVariable long stepId,
                       @Valid @RequestBody UpdateRecipeStepDto dto) {
        service.update(recId, stepId, dto);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @DeleteMapping("/{stepId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long recId,
                       @PathVariable long stepId) {
        service.delete(recId, stepId);
    }
}
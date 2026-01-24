package com.stanbond.homerecipeorganizer.controllers;

import com.stanbond.homerecipeorganizer.DAO.entites.RecipeIng;
import com.stanbond.homerecipeorganizer.DTO.ingredient.CreateIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.recIng.CreateRecipeIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.recIng.UpdateRecipeIngredientDto;
import com.stanbond.homerecipeorganizer.service.RecipeIngService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes/{recId}/ingredients")
@RequiredArgsConstructor
public class RecIngController {

    private final RecipeIngService service;

    @GetMapping
    public ResponseEntity<List<RecipeIng>> getByRecipe(@PathVariable long recId) {
        return ResponseEntity.ok(service.getIngByRecipeId(recId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable long recId,
                       @Valid @RequestBody CreateRecipeIngredientDto dto) {
        service.create(new CreateRecipeIngredientDto(
                recId, dto.ingId(), dto.amount(), dto.unitId()
        ));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @PatchMapping("/{ingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long recId,
                       @PathVariable long ingId,
                       @Valid @RequestBody UpdateRecipeIngredientDto dto) {
        service.update(recId, ingId, dto);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('GOD')")
    @DeleteMapping("/{ingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long recId,
                       @PathVariable long ingId) {
        service.delete(recId, ingId);
    }
}

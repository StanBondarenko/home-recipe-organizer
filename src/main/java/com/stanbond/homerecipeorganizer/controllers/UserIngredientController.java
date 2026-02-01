package com.stanbond.homerecipeorganizer.controllers;

import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredient;
import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredientViewDto;
import com.stanbond.homerecipeorganizer.DTO.userIng.CreateUserIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.userIng.UpdateUserIngredientDto;
import com.stanbond.homerecipeorganizer.service.UserIngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users/me/ingredients")
public class UserIngredientController {

    private final UserIngredientService service;

    public UserIngredientController(UserIngredientService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('GOD')")
    public ResponseEntity<List<UserIngredient>> getAll(Principal principal) {
        return ResponseEntity.ok(service.getAllByLog(principal));
    }
    @GetMapping("/read")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('GOD')")
    public ResponseEntity<List<UserIngredientViewDto>> getMyIngredients(Principal principal) {
        return ResponseEntity.ok(service.getIngByUserIdView(principal));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('GOD')")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateUserIngredientDto dto, Principal principal) {
        service.createByLog(principal, dto);
    }

    @PatchMapping("/{ingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('GOD')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long ingId,
                       @Valid @RequestBody UpdateUserIngredientDto dto,
                       Principal principal) {
        service.updateByLog(principal, ingId, dto);
    }

    @DeleteMapping("/{ingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('GOD')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long ingId, Principal principal) {
        service.deleteByLog(principal, ingId);
    }
}


package com.stanbond.homerecipeorganizer.security.user;

import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredient;
import com.stanbond.homerecipeorganizer.service.UserIngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/my/ingredients")
public class UserIngredientController {

    private final UserIngredientService service;

    public UserIngredientController(UserIngredientService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('GOD')")
    public ResponseEntity<List<UserIngredient>> getAll(Principal principal) {
        return ResponseEntity.ok(service.getAllByEmail(principal.getName()));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('GOD')")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody CreateUserIngredientDto dto, Principal principal) {
        service.createByEmail(principal.getName(), dto);
    }

    @PatchMapping("/{ingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('GOD')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long ingId,
                       @Valid @RequestBody UpdateUserIngredientDto dto,
                       Principal principal) {
        service.updateByEmail(principal.getName(), ingId, dto);
    }

    @DeleteMapping("/{ingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('GOD')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long ingId, Principal principal) {
        service.deleteByEmail(principal.getName(), ingId);
    }
}


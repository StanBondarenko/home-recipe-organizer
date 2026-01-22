package com.stanbond.homerecipeorganizer.DTO.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateIngredientDto(
        @NotBlank(message = "Ingredient name must not be blank")
        @Size(min = 2, max = 50, message = "Ingredient name must be between 2 and 50 characters")
        String name) {}

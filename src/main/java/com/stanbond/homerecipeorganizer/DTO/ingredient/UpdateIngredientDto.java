package com.stanbond.homerecipeorganizer.DTO.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateIngredientDto(
        @NotBlank
        @Size(min = 2, max = 50)
        String name
) {}
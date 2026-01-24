package com.stanbond.homerecipeorganizer.DTO.recipeStep;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRecipeStepDto(
        @NotNull @Min(1) Integer stepNumber,
        @NotBlank String stepText
) {
}

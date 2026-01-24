package com.stanbond.homerecipeorganizer.DTO.recipeStep;

import jakarta.validation.constraints.Min;

public record UpdateRecipeStepDto(
        @Min(1) Integer stepNumber,
        String stepText
) {
}

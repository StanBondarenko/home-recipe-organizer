package com.stanbond.homerecipeorganizer.DTO.recIng;

import jakarta.validation.constraints.*;

public record CreateRecipeIngredientDto(

        @NotNull(message = "Recipe id must be provided")
        @Positive(message = "Recipe id must be greater than zero")
        long recId,

        @NotNull(message = "Ingredient id must be provided")
        @Positive(message = "Ingredient id must be greater than zero")
        long ingId,

        @Positive(message = "Amount must be greater than zero")
        double amount,

        @NotNull(message = "Unit id must be provided")
        @Positive(message = "Unit id must be greater than zero")
        long unitId

) {}
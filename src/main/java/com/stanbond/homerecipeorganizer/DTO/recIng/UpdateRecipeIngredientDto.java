package com.stanbond.homerecipeorganizer.DTO.recIng;

import jakarta.validation.constraints.*;

public record UpdateRecipeIngredientDto(


        @Positive(message = "Amount must be greater than zero")
        Double amount,

        @Positive(message = "Unit id must be greater than zero")
        Integer unitId

) {}

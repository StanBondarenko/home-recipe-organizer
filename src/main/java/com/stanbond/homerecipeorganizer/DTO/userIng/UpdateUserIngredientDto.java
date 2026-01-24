package com.stanbond.homerecipeorganizer.DTO.userIng;

import jakarta.validation.constraints.Positive;

public record UpdateUserIngredientDto(
        @Positive Double amount,
        Long unitId
) {
}

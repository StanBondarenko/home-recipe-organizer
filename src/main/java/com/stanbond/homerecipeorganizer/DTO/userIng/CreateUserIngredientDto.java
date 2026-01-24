package com.stanbond.homerecipeorganizer.DTO.userIng;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateUserIngredientDto(@NotNull Long ingId,
                                      @Positive double amount,
                                      @NotNull Long unitId) {
}

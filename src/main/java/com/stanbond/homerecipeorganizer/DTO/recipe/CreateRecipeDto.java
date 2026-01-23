package com.stanbond.homerecipeorganizer.DTO.recipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateRecipeDto(

        @NotBlank(message = "Recipe name must not be blank")
        @Size(min = 2, max = 50, message = "Recipe name must be between 2 and 50 characters")
        String name,

        @NotBlank(message = "Please put url for picture")
        @Size(min = 2, max = 100, message = "URL must be between 2 and 100 characters")
        String picURL,

        @NotNull(message = "Type id must be provided")
        @Positive(message = "Type id must be greater than zero")
        Integer typeId

        ) {}

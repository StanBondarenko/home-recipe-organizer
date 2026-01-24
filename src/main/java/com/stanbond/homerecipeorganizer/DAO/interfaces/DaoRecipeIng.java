package com.stanbond.homerecipeorganizer.DAO.interfaces;


import com.stanbond.homerecipeorganizer.DAO.entites.Recipe;
import com.stanbond.homerecipeorganizer.DAO.entites.RecipeIng;
import com.stanbond.homerecipeorganizer.DTO.recIng.CreateRecipeIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.recIng.UpdateRecipeIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.recipe.CreateRecipeDto;
import com.stanbond.homerecipeorganizer.DTO.recipe.UpdateRecipeDto;

import java.util.List;
import java.util.Optional;

public interface DaoRecipeIng {
    Optional<List<RecipeIng>> getIngByRecId(long id);
    void create(CreateRecipeIngredientDto dto);
    void update(long recId, long ingId, UpdateRecipeIngredientDto dto);
    void delete(long recId, long ingId);
}

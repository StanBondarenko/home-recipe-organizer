package com.stanbond.homerecipeorganizer.DAO.interfaces;

import com.stanbond.homerecipeorganizer.DAO.entites.Recipe;
import com.stanbond.homerecipeorganizer.DTO.recipe.CreateRecipeDto;
import com.stanbond.homerecipeorganizer.DTO.recipe.UpdateRecipeDto;

import java.util.Optional;
import java.util.List;
public interface DaoRecipe {
    Optional<List<Recipe>> getAll();
    Optional<List<Recipe>> getByName(String name);
    Optional<Recipe> getById(long id);
    Optional<List<Recipe>> getByTypeName(String typeName);
    Optional<List<Recipe>> getByTypeNameAndName(String typeName, String name);
    Recipe create(CreateRecipeDto dto);
    Recipe update(long id,UpdateRecipeDto dto);
    void delete(long id);
}

package com.stanbond.homerecipeorganizer.DAO.interfaces;
import com.stanbond.homerecipeorganizer.DAO.entites.RecipeStep;
import com.stanbond.homerecipeorganizer.DTO.recipeStep.CreateRecipeStepDto;
import com.stanbond.homerecipeorganizer.DTO.recipeStep.UpdateRecipeStepDto;

import java.util.List;
import java.util.Optional;

public interface RecipeStepDao {

    List<RecipeStep> getByRecipeId(long recId);

    Optional<RecipeStep> getOne(long recId, long stepId);

    long create(long recId, CreateRecipeStepDto dto);

    void update(long recId, long stepId, UpdateRecipeStepDto dto);

    void delete(long recId, long stepId);
}

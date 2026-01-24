package com.stanbond.homerecipeorganizer.service;
import com.stanbond.homerecipeorganizer.DAO.entites.RecipeStep;
import com.stanbond.homerecipeorganizer.DAO.interfaces.RecipeStepDao;
import com.stanbond.homerecipeorganizer.DTO.recipeStep.CreateRecipeStepDto;
import com.stanbond.homerecipeorganizer.DTO.recipeStep.UpdateRecipeStepDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeStepService {

    private final RecipeStepDao dao;

    public RecipeStepService(RecipeStepDao dao) {
        this.dao = dao;
    }

    public List<RecipeStep> getSteps(long recId) {
        return dao.getByRecipeId(recId);
    }

    public long create(long recId, CreateRecipeStepDto dto) {
        return dao.create(recId, dto);
    }

    public void update(long recId, long stepId, UpdateRecipeStepDto dto) {
        dao.update(recId, stepId, dto);
    }

    public void delete(long recId, long stepId) {
        dao.delete(recId, stepId);
    }
}

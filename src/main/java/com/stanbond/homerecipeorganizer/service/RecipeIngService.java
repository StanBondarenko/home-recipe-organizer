package com.stanbond.homerecipeorganizer.service;

import com.stanbond.homerecipeorganizer.DAO.entites.RecipeIng;
import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoRecipeIng;
import com.stanbond.homerecipeorganizer.DTO.recIng.CreateRecipeIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.recIng.UpdateRecipeIngredientDto;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RecipeIngService {
    private final DaoRecipeIng dao;

    public RecipeIngService(DaoRecipeIng dao) {
        this.dao = dao;
    }
    public List<RecipeIng> getIngByRecipeId(long recipeId){
        return dao.getIngByRecId(recipeId).orElseThrow(()-> new NotFoundException( "No recipe with this ingredient id"));
    }
    public void create(CreateRecipeIngredientDto dto){
        dao.create(dto);
    }
    public void update(long recId, long ingId, UpdateRecipeIngredientDto dto){
        dao.update(recId, ingId, dto);
    }
    @Transactional
    public void delete(long recId, long ingId){
        dao.delete(recId,ingId);
    }
}

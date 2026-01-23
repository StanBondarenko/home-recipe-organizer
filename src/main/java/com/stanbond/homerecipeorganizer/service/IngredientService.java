package com.stanbond.homerecipeorganizer.service;

import com.stanbond.homerecipeorganizer.DAO.entites.Ingredient;
import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoIngredient;
import com.stanbond.homerecipeorganizer.DTO.ingredient.UpdateIngredientDto;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class IngredientService {
    private final DaoIngredient daoIngredient;

    public List<Ingredient> getAllIngrediets(){
        return daoIngredient.getAllIng();
    }
    public Ingredient getIngById(long id){
        return daoIngredient.getIngByid(id)
                .orElseThrow(()-> new NotFoundException("No ingredient with that id"));
    }
    public Ingredient getIngByName(String name){
        return daoIngredient.getIngByName(name)
                .orElseThrow(()-> new NotFoundException("No ingredient with that name"));
    }
    public Ingredient createIng(String name){
        return daoIngredient.createIng(name);
    }
    public Ingredient updateIng(int id, UpdateIngredientDto blank){
        return daoIngredient.updateIng(id,blank);
    }
}

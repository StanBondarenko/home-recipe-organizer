package com.stanbond.homerecipeorganizer.DAO.interfaces;

import com.stanbond.homerecipeorganizer.DAO.entites.Ingredient;
import com.stanbond.homerecipeorganizer.DTO.ingredient.UpdateIngredientDto;

import java.util.List;
import java.util.Optional;

public interface DaoIngredient {
    List<Ingredient> getAllIng();
    Optional<Ingredient> getIngByid(long id);
    Optional<Ingredient> getIngByName(String name);
    Ingredient createIng(String name);
    Ingredient updateIng(int id, UpdateIngredientDto blank);
}

package com.stanbond.homerecipeorganizer.DAO.interfaces;

import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredient;
import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredientViewDto;
import com.stanbond.homerecipeorganizer.DTO.userIng.CreateUserIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.userIng.UpdateUserIngredientDto;

import java.util.List;
import java.util.Optional;

public interface UserIngredientDao {
    List<UserIngredient> getAllByUserId(long userId);

    Optional<UserIngredient> getOne(long userId, long ingId);

    List<UserIngredientViewDto> getMyIngredients(long userId);

    void create(long userId, CreateUserIngredientDto dto);

    void update(long userId, long ingId, UpdateUserIngredientDto dto);

    void delete(long userId, long ingId);
}

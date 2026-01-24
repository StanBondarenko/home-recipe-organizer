package com.stanbond.homerecipeorganizer.service;


import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredient;
import com.stanbond.homerecipeorganizer.DAO.interfaces.UserIngredientDao;
import com.stanbond.homerecipeorganizer.DTO.userIng.CreateUserIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.userIng.UpdateUserIngredientDto;
import com.stanbond.homerecipeorganizer.security.user.User;
import com.stanbond.homerecipeorganizer.security.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserIngredientService {

    private final UserIngredientDao dao;
    private final UserService userService;

    public UserIngredientService(UserIngredientDao dao, UserService userService) {
        this.dao = dao;
        this.userService = userService;
    }

    private long requireUserId(String email) {
        User user = userService.getUserByEmail(email);
        return user.getUserId();
    }

    public List<UserIngredient> getAllByEmail(String email) {
        return dao.getAllByUserId(requireUserId(email));
    }

    public void createByEmail(String email, CreateUserIngredientDto dto) {
        dao.create(requireUserId(email), dto);
    }

    public void updateByEmail(String email, long ingId, UpdateUserIngredientDto dto) {
        dao.update(requireUserId(email), ingId, dto);
    }

    public void deleteByEmail(String email, long ingId) {
        dao.delete(requireUserId(email), ingId);
    }
}


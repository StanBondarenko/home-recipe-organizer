package com.stanbond.homerecipeorganizer.service;


import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredient;
import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredientViewDto;
import com.stanbond.homerecipeorganizer.DAO.interfaces.UserIngredientDao;
import com.stanbond.homerecipeorganizer.DTO.userIng.CreateUserIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.userIng.UpdateUserIngredientDto;
import com.stanbond.homerecipeorganizer.security.user.User;
import com.stanbond.homerecipeorganizer.security.user.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserIngredientService {

    private final UserIngredientDao dao;
    private final UserService userService;

    public UserIngredientService(UserIngredientDao dao, UserService userService) {
        this.dao = dao;
        this.userService = userService;
    }

    private long requireUserId(Principal principal) {
        User user = userService.getUserByLogin(principal);
        return user.getUserId();
    }

    public List<UserIngredient> getAllByLog(Principal principal) {
        return dao.getAllByUserId(requireUserId(principal));
    }
    public  List<UserIngredientViewDto> getIngByUserIdView(Principal principal ){
        return  dao.getMyIngredients(requireUserId(principal));
    }

    public void createByLog(Principal principal, CreateUserIngredientDto dto) {
        dao.create(requireUserId(principal), dto);
    }

    public void updateByLog(Principal principal, long ingId, UpdateUserIngredientDto dto) {
        dao.update(requireUserId(principal), ingId, dto);
    }

    public void deleteByLog(Principal principal, long ingId) {
        dao.delete(requireUserId(principal), ingId);
    }
}


package com.stanbond.homerecipeorganizer.DAO.interfaces;

import com.stanbond.homerecipeorganizer.DAO.entites.UserRole;

import java.util.List;


public interface DaoUserRole {
     List<String> findRoleNamesByUserId(long userId);
     void createNewUserRole(long userId, long roleId);
     void updateNewUserRole(long userId);
     void deleteUserRole(long id);
}

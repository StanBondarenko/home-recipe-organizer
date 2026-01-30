package com.stanbond.homerecipeorganizer.service;
import com.stanbond.homerecipeorganizer.DAO.entites.UserFavorite;
import com.stanbond.homerecipeorganizer.DAO.interfaces.UserFavoriteDao;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import com.stanbond.homerecipeorganizer.security.user.User;
import com.stanbond.homerecipeorganizer.security.user.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserFavoriteService {

    private final UserFavoriteDao dao;
    private final UserService userService;

    public UserFavoriteService(UserFavoriteDao dao, UserService userService) {
        this.dao = dao;
        this.userService = userService;
    }
    public List<UserFavorite> getByUserIdAndRecId(Principal principal, long recID){
        User user = userService.getUserByLogin(principal);
        return dao.getByRecId(user.getUserId(),recID).orElseThrow(()-> new NotFoundException("No recipe with this id"));
    }

    private long requireUserId(Principal principal) {
        User user = userService.getUserByLogin(principal);
        return user.getUserId();
    }

    public List<UserFavorite> getAll(Principal principal) {
        return dao.getAllByUserId(requireUserId(principal));
    }

    public void addByEmail(Principal principal, long recId) {
        dao.add(requireUserId(principal), recId);
    }

    public void removeByEmail(Principal principal, long recId) {
        dao.remove(requireUserId(principal), recId);
    }
}

package com.stanbond.homerecipeorganizer.service;
import com.stanbond.homerecipeorganizer.DAO.entites.UserFavorite;
import com.stanbond.homerecipeorganizer.DAO.interfaces.UserFavoriteDao;
import com.stanbond.homerecipeorganizer.security.user.User;
import com.stanbond.homerecipeorganizer.security.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavoriteService {

    private final UserFavoriteDao dao;
    private final UserService userService;

    public UserFavoriteService(UserFavoriteDao dao, UserService userService) {
        this.dao = dao;
        this.userService = userService;
    }

    private long requireUserId(String email) {
        User user = userService.getUserByEmail(email);
        return user.getUserId();
    }

    public List<UserFavorite> getAllByEmail(String email) {
        return dao.getAllByUserId(requireUserId(email));
    }

    public void addByEmail(String email, long recId) {
        dao.add(requireUserId(email), recId);
    }

    public void removeByEmail(String email, long recId) {
        dao.remove(requireUserId(email), recId);
    }
}

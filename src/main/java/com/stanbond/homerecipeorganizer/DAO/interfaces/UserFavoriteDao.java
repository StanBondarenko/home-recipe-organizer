package com.stanbond.homerecipeorganizer.DAO.interfaces;
import com.stanbond.homerecipeorganizer.DAO.entites.UserFavorite;

import java.util.List;

public interface UserFavoriteDao {

    List<UserFavorite> getAllByUserId(long userId);

    void add(long userId, long recId);

    void remove(long userId, long recId);
}
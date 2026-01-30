package com.stanbond.homerecipeorganizer.DAO.interfaces;
import com.stanbond.homerecipeorganizer.DAO.entites.UserFavorite;

import java.util.List;
import java.util.Optional;

public interface UserFavoriteDao {

    List<UserFavorite> getAllByUserId(long userId);
    Optional<List<UserFavorite>> getByRecId( long userId,long recId);
    void add(long userId, long recId);

    void remove(long userId, long recId);
}
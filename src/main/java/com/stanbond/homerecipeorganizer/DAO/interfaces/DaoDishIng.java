package com.stanbond.homerecipeorganizer.DAO.interfaces;

import com.stanbond.homerecipeorganizer.DAO.entites.DishIng;

import java.util.List;
import java.util.Optional;

public interface DaoDishIng {
    Optional<List<DishIng>> getByRecId(long recId);
}

package com.stanbond.homerecipeorganizer.service;

import com.stanbond.homerecipeorganizer.DAO.entites.DishIng;
import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoDishIng;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DishIngService {
    private final DaoDishIng dao;

    public DishIngService(DaoDishIng dao) {
        this.dao = dao;
    }
    public List<DishIng> getIngByRecId(long recId){
        return dao.getByRecId(recId).orElseThrow(()-> new NotFoundException("No recipe with this recipe id"));
    }
}

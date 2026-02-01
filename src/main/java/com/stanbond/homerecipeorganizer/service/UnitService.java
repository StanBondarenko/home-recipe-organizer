package com.stanbond.homerecipeorganizer.service;

import com.stanbond.homerecipeorganizer.DAO.entites.Unit;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import com.stanbond.homerecipeorganizer.repositores.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {
    private final UnitRepository repo;

    public List<Unit> getAllUnits(){
        List<Unit> all = repo.findAll();
        if(all.isEmpty()){
            return List.of();
        }else {
            return all;
        }
    }
    public Unit getByCode(String code){
        return repo.findByCode(code).orElseThrow(()-> new NotFoundException("No unit with this code"));
    }
    public Unit findById(long id){
        return repo.findById(id).orElseThrow(()->new NotFoundException("No unit with this id"));
    }

}

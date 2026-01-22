package com.stanbond.homerecipeorganizer.service;

import com.stanbond.homerecipeorganizer.DAO.entites.Role;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import com.stanbond.homerecipeorganizer.repositores.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository repo;

    public Role getRoleById(long id){
        return repo.findById(id)
                .orElseThrow(()-> new NotFoundException("Role with id: "+id+"not found"));
    }
}

package com.stanbond.homerecipeorganizer.service;

import com.stanbond.homerecipeorganizer.DAO.entites.Recipe;
import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoRecipe;
import com.stanbond.homerecipeorganizer.DTO.recipe.CreateRecipeDto;
import com.stanbond.homerecipeorganizer.DTO.recipe.UpdateRecipeDto;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RecipeService {
    private final DaoRecipe dao;

    public RecipeService(DaoRecipe dao) {
        this.dao = dao;
    }

    private List<Recipe> getAll() {
        return dao.getAll().orElseThrow(() -> new NotFoundException("Cannot get all recipes"));
    }
    private List<Recipe> getByName(String name){
        return  dao.getByName(name).orElseThrow(()-> new NotFoundException("No recipe with this name"));
    }
    private List<Recipe> getById(long id){
        return List.of(dao.getById(id).orElseThrow(()-> new NotFoundException("No recipe with this id")));
    }
    private  List<Recipe> getByTypeName(String name){
        return dao.getByTypeName(name).orElseThrow(()-> new NotFoundException("No recipe with this type"));
    }
    private List<Recipe> getByTypeAndName(String type, String name){
        return dao.getByTypeNameAndName(type,name).orElseThrow(()-> new NotFoundException("No recipe with this type and name"));
    }
    public List<Recipe> find(String name, long id, String typeName){
        if(!typeName.isBlank() && !name.isBlank() && id == 0){
            name = name.trim();
            return getByTypeAndName(typeName,name);
        }else if(name.isBlank() && id == 0 && !typeName.isBlank()){
            typeName = typeName.trim();
            return getByTypeName(typeName);
        }else if (name.isBlank() && typeName.isBlank() && id > 0) {
            return getById(id);
        }else if(typeName.isBlank() && id == 0 && !name.isBlank()){
            name = name.trim();
            return getByName(name);
        }else {
            return getAll();
        }
    }
    public Recipe create(CreateRecipeDto dto){
        return dao.create(dto);
    }
    public Recipe update(long id, UpdateRecipeDto dto){
        return dao.update(id, dto);
    }
    @Transactional
    public void deleteRecipe(long id) {
        dao.delete(id);
    }
}

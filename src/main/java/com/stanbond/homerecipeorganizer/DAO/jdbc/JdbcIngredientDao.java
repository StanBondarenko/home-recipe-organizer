package com.stanbond.homerecipeorganizer.DAO.jdbc;

import com.stanbond.homerecipeorganizer.DAO.entites.Ingredient;
import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoIngredient;
import com.stanbond.homerecipeorganizer.DAO.mappers.IngredientMapper;
import com.stanbond.homerecipeorganizer.DTO.ingredient.UpdateIngredientDto;
import com.stanbond.homerecipeorganizer.exceptions.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientDao  implements DaoIngredient {
    private final JdbcTemplate template;
    private final IngredientMapper mapper = new IngredientMapper();
    public JdbcIngredientDao(JdbcTemplate template){
        this.template = template;
    }

    @Override
    public List<Ingredient> getAllIng() {
        String sql= """
                SELECT ing_id, ing_name
                FROM ingredient
                ORDER by ing_id;""";
        try {
            return template.query(sql,mapper);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to database");
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data problems");
        }
    }

    @Override
    public Optional<Ingredient> getIngByid(long id) {
        String sql= """
                SELECT ing_id, ing_name
                FROM ingredient
                WHERE ing_id = ?;""";
        try{
            return template.query(sql,mapper,id)
                    .stream()
                    .findFirst();
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to database",e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Bad Data",e );
        }
    }

    @Override
    public Optional<Ingredient> getIngByName(String name) {
        String sql= """
                SELECT ing_id, ing_name
                FROM ingredient
                WHERE ing_name ILIKE ?;""";
        try{
            return template.query(sql,mapper,name)
                    .stream().findFirst();
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to database",e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Bad Data",e);
        }
    }

    @Override
    public Ingredient createIng(String name) {
        String sql = """
                INSERT INTO ingredient (ing_name)
                VALUES (?)
                RETURNING ing_id;""";
        try{
            if (getIngByName(name).isPresent()) {
                throw new DaoException("Ingredient already exists");
            }
            Integer id = template.queryForObject(sql, Integer.class, name);
            if (id == null) {
                throw new DaoException("Insert returned null ing_id");
            }
            return getIngByid(id.longValue())
                    .orElseThrow(() -> new DaoException("Ingredient was not found after insert"));
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to database");
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Bad Data");
        }
    }

    @Override
    public Ingredient updateIng(int id, UpdateIngredientDto blank) {
        String sql = """
                UPDATE ingredient
                SET ing_name=?
                WHERE ing_id =?;""";
        try {
            int row = template.update(sql,blank.name(),id);
            if(row==0){
                throw new DaoException("Zero rows affected, expected at least one");
            }else {
                return getIngByid(id).get();
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to database");
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Bad Data");
        }

    }

}

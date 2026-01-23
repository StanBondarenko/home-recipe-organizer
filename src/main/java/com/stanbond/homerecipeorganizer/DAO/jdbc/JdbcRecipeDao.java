package com.stanbond.homerecipeorganizer.DAO.jdbc;

import com.stanbond.homerecipeorganizer.DAO.entites.Recipe;
import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoRecipe;
import com.stanbond.homerecipeorganizer.DAO.mappers.RecipeMapper;
import com.stanbond.homerecipeorganizer.DTO.recipe.CreateRecipeDto;
import com.stanbond.homerecipeorganizer.DTO.recipe.UpdateRecipeDto;
import com.stanbond.homerecipeorganizer.exceptions.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JdbcRecipeDao implements DaoRecipe {
    private final JdbcTemplate template;
    private RecipeMapper mapper = new RecipeMapper();
    public JdbcRecipeDao(JdbcTemplate template){
        this.template = template;
    }
    @Override
    public Optional<List<Recipe>> getAll() {
        String sql = """
                SELECT rec_id, rec_name, pic_url, type_id
                FROM recipe
                ORDER BY rec_id;""";
        try{
           List<Recipe> res = template.query(sql,mapper);
           return Optional.of(res);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to base", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public Optional<Recipe> getByName(String name) {
        String sql = """
                SELECT rec_id, rec_name, pic_url, type_id
                FROM recipe
                WHERE rec_name LIKE ?
                ORDER BY rec_id;""";
        try{
            return template.query(sql, mapper, name).stream().findFirst();
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to base", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public Optional<Recipe> getById(long id) {
        String sql = """
                SELECT rec_id, rec_name, pic_url, type_id
                FROM recipe
                WHERE rec_id = ?
                ORDER BY rec_id;""";
        try{
         return template.query(sql,mapper,id).stream().findFirst();
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to base", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public Optional<List<Recipe>> getByTypeName(String typeName) {
        String sql = """
                SELECT rec_id, rec_name, pic_url, type_id
                FROM recipe
                JOIN dish_type USING(type_id)
                WHERE type_name LIKE ?
                ORDER BY rec_id;""";
        try {
            List<Recipe> result = template.query(sql,mapper,typeName);
            return Optional.of(result);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to base", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public Recipe create(CreateRecipeDto dto) {
        String sql = """
                INSERT INTO Recipe(rec_name,pic_url, type_id)
                VALUES (?,?,?)
                RETURNING rec_id""";
        try {
            if(getByName(dto.name()).isPresent()){
                throw new DaoException("Dish already exists");
            }
            Integer id = template.queryForObject(sql, Integer.class,dto.name(),dto.picURL(),dto.typeId());
            if (id == null) {
                throw new DaoException("Insert returned null ing_id");
            }
            return getById(id.longValue()).orElseThrow(() -> new DaoException("Recipe was not found after insert"));
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to base", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public Recipe update(long id, UpdateRecipeDto dto) {
        String sql = """
                UPDATE recipe
                SET rec_name = ?, pic_url = ?, type_id = ?
                WHERE rec_id = ?;""";
        try{
            int row = template.update(sql, dto.name(),dto.picURL(), dto.typeId(), id);
            if(row==0){
                throw new DaoException("Zero rows affected, expected at least one");
            }else {
                return getById(id).get();
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to base", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public void delete(long id) {
        String deleteUF = """
                DELETE FROM user_favorite
                WHERE rec_id = ?;""";
        String deleteRI = """
                DELETE FROM recipe_ingredient
                WHERE rec_id = ?;""";
        String deleteRS = """
                DELETE FROM recipe_step
                WHERE rec_id = ?;""";
        String deleteRec = """
                DELETE FROM recipe
                WHERE rec_id = ?;""";
        try {
            template.update(deleteUF,id);
            template.update(deleteRI,id);
            template.update(deleteRS, id);
            template.update(deleteRec,id);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to base", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data problems", e);
        }
    }
}

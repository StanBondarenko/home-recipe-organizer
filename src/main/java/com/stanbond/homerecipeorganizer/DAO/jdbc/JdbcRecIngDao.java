package com.stanbond.homerecipeorganizer.DAO.jdbc;

import com.stanbond.homerecipeorganizer.DAO.entites.RecipeIng;
import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoRecipeIng;
import com.stanbond.homerecipeorganizer.DAO.mappers.RecipeIngMapper;
import com.stanbond.homerecipeorganizer.DTO.recIng.CreateRecipeIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.recIng.UpdateRecipeIngredientDto;
import com.stanbond.homerecipeorganizer.exceptions.DaoException;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JdbcRecIngDao implements DaoRecipeIng {
    private final JdbcTemplate template;
    private RecipeIngMapper mapper = new RecipeIngMapper();
    public JdbcRecIngDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<List<RecipeIng>> getIngByRecId(long id) {
        String sql = """
                SELECT rec_id, ing_id, amount, unit_id, amount_base
                FROM recipe_ingredient
                WHERE rec_id = ?
                ORDER BY ing_id;""";
        try{
            List<RecipeIng> all = template.query(sql,mapper,id);
            return Optional.of(all);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to base", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public void create(CreateRecipeIngredientDto dto) {

        String getToBaseSql = """
            SELECT to_base
            FROM unit
            WHERE unit_id = ?
            """;

        String insertSql = """
            INSERT INTO recipe_ingredient(rec_id, ing_id, amount, unit_id, amount_base)
            VALUES (?, ?, ?, ?, ?)
            """;
        try {
            List<RecipeIng> recipeIngs = getIngByRecId(dto.recId()).orElseThrow(()-> new NotFoundException("\"No recipe with this ingredient id\""));
            for(RecipeIng pair: recipeIngs){
                if(dto.ingId() == pair.getIngId()){
                    throw new DaoException("The ingredient is already in the recipe");
                }
            }
            Double toBase = template.queryForObject(getToBaseSql, Double.class, dto.unitId());
            if (toBase == null) {
                throw new DaoException("Unit not found: " + dto.unitId());
            }
            double amountBase = dto.amount() * toBase;
            template.update(insertSql,
                    dto.recId(),
                    dto.ingId(),
                    dto.amount(),
                    dto.unitId(),
                    amountBase
            );
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public void update(long recId, long ingId, UpdateRecipeIngredientDto dto) {

        String sql = """
        UPDATE recipe_ingredient ri
        SET
            amount = COALESCE(?, ri.amount),
            unit_id = COALESCE(?, ri.unit_id),
            amount_base = COALESCE(?, ri.amount) * u.to_base
        FROM unit u
        WHERE ri.rec_id = ?
          AND ri.ing_id = ?
          AND u.unit_id = COALESCE(?, ri.unit_id)
        """;
        try {
            int rows = template.update(
                    sql,
                    dto.amount(),
                    dto.unitId(),
                    dto.amount(),
                    recId,
                    ingId,
                    dto.unitId()
            );
            if (rows == 0) {
                throw new DaoException("Recipe ingredient not found or unit invalid. recId=" + recId + ", ingId=" + ingId);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public void delete(long recId, long ingId) {

        String sql = """
        DELETE FROM recipe_ingredient
        WHERE rec_id = ? AND ing_id = ?
        """;

        try {
            int rows = template.update(sql, recId, ingId);

            if (rows == 0) {
                throw new DaoException("Recipe ingredient not found. recId=" + recId + ", ingId=" + ingId);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data problems", e);
        }
    }


}

package com.stanbond.homerecipeorganizer.DAO.jdbc;

import com.stanbond.homerecipeorganizer.DAO.entites.RecipeStep;
import com.stanbond.homerecipeorganizer.DAO.interfaces.RecipeStepDao;
import com.stanbond.homerecipeorganizer.DAO.mappers.RecipeStepRowMapper;
import com.stanbond.homerecipeorganizer.DTO.recipeStep.CreateRecipeStepDto;
import com.stanbond.homerecipeorganizer.DTO.recipeStep.UpdateRecipeStepDto;
import com.stanbond.homerecipeorganizer.exceptions.DaoException;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcRecipeStepDao implements RecipeStepDao {

    private final JdbcTemplate template;
    private final RecipeStepRowMapper mapper = new RecipeStepRowMapper();

    public JdbcRecipeStepDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<RecipeStep> getByRecipeId(long recId) {
        String sql = """
                SELECT step_id, rec_id, step_number, step_text
                FROM recipe_step
                WHERE rec_id = ?
                ORDER BY step_number
                """;
        try {
            return template.query(sql, mapper, recId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        }
    }

    @Override
    public Optional<RecipeStep> getOne(long recId, long stepId) {
        String sql = """
                SELECT step_id, rec_id, step_number, step_text
                FROM recipe_step
                WHERE rec_id = ? AND step_id = ?
                """;
        try {
            List<RecipeStep> list = template.query(sql, mapper, recId, stepId);
            return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        }
    }


    @Override
    public long create(long recId, CreateRecipeStepDto dto) {
        String sql = """
                INSERT INTO recipe_step(rec_id, step_number, step_text)
                VALUES (?, ?, ?)
                RETURNING step_id
                """;
        try {
            Long stepId = template.queryForObject(sql, Long.class, recId, dto.stepNumber(), dto.stepText());
            if (stepId == null) {
                throw new DaoException("Failed to create recipe step");
            }
            return stepId;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public void update(long recId, long stepId, UpdateRecipeStepDto dto) {
        String sql = """
                UPDATE recipe_step
                SET
                    step_number = COALESCE(?, step_number),
                    step_text   = COALESCE(?, step_text)
                WHERE rec_id = ? AND step_id = ?
                """;
        try {
            int rows = template.update(sql, dto.stepNumber(), dto.stepText(), recId, stepId);
            if (rows == 0) {
                throw new NotFoundException("Step not found. recId=" + recId + ", stepId=" + stepId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public void delete(long recId, long stepId) {
        String sql = """
                DELETE FROM recipe_step
                WHERE rec_id = ? AND step_id = ?
                """;
        try {
            int rows = template.update(sql, recId, stepId);
            if (rows == 0) {
                throw new NotFoundException("Step not found. recId=" + recId + ", stepId=" + stepId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        }
    }
}
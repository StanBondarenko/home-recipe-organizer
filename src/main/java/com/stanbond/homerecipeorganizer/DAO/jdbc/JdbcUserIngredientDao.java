package com.stanbond.homerecipeorganizer.DAO.jdbc;


import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredient;
import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredientViewDto;
import com.stanbond.homerecipeorganizer.DAO.interfaces.UserIngredientDao;
import com.stanbond.homerecipeorganizer.DAO.mappers.UserIngredientRowMapper;
import com.stanbond.homerecipeorganizer.DTO.userIng.CreateUserIngredientDto;
import com.stanbond.homerecipeorganizer.DTO.userIng.UpdateUserIngredientDto;
import com.stanbond.homerecipeorganizer.exceptions.DaoException;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserIngredientDao implements UserIngredientDao {

    private final JdbcTemplate template;
    private final UserIngredientRowMapper mapper = new UserIngredientRowMapper();

    public JdbcUserIngredientDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<UserIngredient> getAllByUserId(long userId) {
        String sql = """
                SELECT user_id, ing_id, amount, unit_id, amount_base
                FROM user_ingredient
                WHERE user_id = ?
                ORDER BY ing_id
                """;
        try {
            return template.query(sql, mapper, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        }
    }

    @Override
    public List<UserIngredientViewDto> getMyIngredients(long userId) {
        String sql = """
            SELECT i.ing_name, ui.amount, u.code
            FROM user_ingredient ui
            JOIN ingredient i USING(ing_id)
            JOIN unit u USING(unit_id)
            WHERE ui.user_id = ?
            ORDER BY i.ing_name;
        """;

        return template.query(
                sql,
                (rs, rowNum) -> new UserIngredientViewDto(
                        rs.getString("ing_name"),
                        rs.getBigDecimal("amount"),
                        rs.getString("code")
                ),
                userId
        );
    }

    @Override
    public Optional<UserIngredient> getOne(long userId, long ingId) {
        String sql = """
                SELECT user_id, ing_id, amount, unit_id, amount_base
                FROM user_ingredient
                WHERE user_id = ? AND ing_id = ?
                """;
        try {
            List<UserIngredient> list = template.query(sql, mapper, userId, ingId);
            return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        }
    }


    @Override
    public void create(long userId, CreateUserIngredientDto dto) {

        String toBaseSql = """
                SELECT to_base
                FROM unit
                WHERE unit_id = ?
                """;

        String insertSql = """
                INSERT INTO user_ingredient(user_id, ing_id, amount, unit_id, amount_base)
                VALUES (?, ?, ?, ?, ?)
                """;

        try {
            BigDecimal toBase = template.queryForObject(toBaseSql, BigDecimal.class, dto.unitId());
            if (toBase == null) {
                throw new NotFoundException("Unit not found: " + dto.unitId());
            }

            BigDecimal amount = BigDecimal.valueOf(dto.amount());
            BigDecimal amountBase = amount.multiply(toBase);

            template.update(
                    insertSql,
                    userId,
                    dto.ingId(),
                    amount,
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
    public void update(long userId, long ingId, UpdateUserIngredientDto dto) {

        String sql = """
                UPDATE user_ingredient ui
                SET
                    amount = COALESCE(?, ui.amount),
                    unit_id = COALESCE(?, ui.unit_id),
                    amount_base = COALESCE(?, ui.amount) * u.to_base
                FROM unit u
                WHERE ui.user_id = ?
                  AND ui.ing_id = ?
                  AND u.unit_id = COALESCE(?, ui.unit_id)
                """;

        try {
            BigDecimal amount = dto.amount() == null ? null : BigDecimal.valueOf(dto.amount());

            int rows = template.update(
                    sql,
                    amount,
                    dto.unitId(),
                    amount,
                    userId,
                    ingId,
                    dto.unitId()
            );

            if (rows == 0) {
                throw new NotFoundException("User ingredient not found or unit invalid. userId=" + userId + ", ingId=" + ingId);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public void delete(long userId, long ingId) {
        String sql = """
                DELETE FROM user_ingredient
                WHERE user_id = ? AND ing_id = ?
                """;
        try {
            int rows = template.update(sql, userId, ingId);
            if (rows == 0) {
                throw new NotFoundException("User ingredient not found. userId=" + userId + ", ingId=" + ingId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        }
    }
}

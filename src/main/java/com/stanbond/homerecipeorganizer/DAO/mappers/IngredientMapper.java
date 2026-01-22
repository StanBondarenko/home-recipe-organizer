package com.stanbond.homerecipeorganizer.DAO.mappers;

import com.stanbond.homerecipeorganizer.DAO.entites.Ingredient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getLong("ing_id"));
        ingredient.setIngName(rs.getString("ing_name"));
        return ingredient;
    }
}

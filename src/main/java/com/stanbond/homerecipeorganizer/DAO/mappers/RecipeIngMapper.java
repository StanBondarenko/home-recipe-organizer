package com.stanbond.homerecipeorganizer.DAO.mappers;

import com.stanbond.homerecipeorganizer.DAO.entites.RecipeIng;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeIngMapper implements RowMapper<RecipeIng> {

    @Override
    public RecipeIng mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecipeIng recipeIng = new RecipeIng();
        recipeIng.setIngId(rs.getLong("ing_id"));
        recipeIng.setRecId(rs.getLong("rec_id"));
        recipeIng.setAmount(rs.getDouble("amount"));
        recipeIng.setUnitId(rs.getLong("unit_id"));
        recipeIng.setAmountBase(rs.getDouble("amount_base"));
        return recipeIng;
    }
}

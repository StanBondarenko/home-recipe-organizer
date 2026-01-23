package com.stanbond.homerecipeorganizer.DAO.mappers;

import com.stanbond.homerecipeorganizer.DAO.entites.Recipe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getLong("rec_id"));
        recipe.setRecipeName(rs.getString("rec_name"));
        recipe.setPicURL(rs.getString("pic_url"));
        recipe.setTypeId(rs.getLong("type_id"));
        return recipe;
    }
}

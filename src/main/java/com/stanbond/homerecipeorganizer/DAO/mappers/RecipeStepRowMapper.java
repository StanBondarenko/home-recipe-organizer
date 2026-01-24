package com.stanbond.homerecipeorganizer.DAO.mappers;


import com.stanbond.homerecipeorganizer.DAO.entites.RecipeStep;
import org.springframework.jdbc.core.RowMapper;


import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeStepRowMapper implements RowMapper<RecipeStep> {
    @Override
    public RecipeStep mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecipeStep step = new RecipeStep();
        step.setStepId(rs.getLong("step_id"));
        step.setRecId(rs.getLong("rec_id"));
        step.setStepNumber(rs.getInt("step_number"));
        step.setStepText(rs.getString("step_text"));
        return step;
    }
}
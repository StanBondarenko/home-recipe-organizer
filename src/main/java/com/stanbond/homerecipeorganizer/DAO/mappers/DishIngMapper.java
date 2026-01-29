package com.stanbond.homerecipeorganizer.DAO.mappers;

import com.stanbond.homerecipeorganizer.DAO.entites.DishIng;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishIngMapper implements RowMapper<DishIng> {
    @Override
    public DishIng mapRow(ResultSet rs, int rowNum) throws SQLException {
        DishIng dish = new DishIng();
        dish.setIngName(rs.getString("ing_name"));
        dish.setAmount(rs.getDouble("amount"));
        dish.setCode(rs.getString("code"));
        return dish;
    }
}

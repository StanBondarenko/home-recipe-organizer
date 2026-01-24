package com.stanbond.homerecipeorganizer.DAO.mappers;

import com.stanbond.homerecipeorganizer.DAO.entites.UserIngredient;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIngredientRowMapper implements RowMapper<UserIngredient> {
    @Override
    public UserIngredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserIngredient ui = new UserIngredient();
        ui.setUserId(rs.getLong("user_id"));
        ui.setIngId(rs.getLong("ing_id"));
        BigDecimal amount = rs.getBigDecimal("amount");
        ui.setAmount(amount != null ? amount.doubleValue() : 0.0);
        ui.setUnitId(rs.getLong("unit_id"));
        BigDecimal amountBase = rs.getBigDecimal("amount_base");
        ui.setAmountBase(amountBase != null ? amountBase.doubleValue() : null);
        return ui;
    }
}

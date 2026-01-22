package com.stanbond.homerecipeorganizer.DAO.mappers;

import com.stanbond.homerecipeorganizer.DAO.entites.UserRole;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMapper implements RowMapper<UserRole> {

    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRole newUserRole = new UserRole();
        newUserRole.setRoleId(rs.getLong("role_id"));
        newUserRole.setUserId(rs.getLong("user_id"));
        return newUserRole;
    }
}

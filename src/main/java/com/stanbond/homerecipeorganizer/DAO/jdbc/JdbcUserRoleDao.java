package com.stanbond.homerecipeorganizer.DAO.jdbc;

import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoUserRole;
import com.stanbond.homerecipeorganizer.exceptions.DaoException;
import com.stanbond.homerecipeorganizer.DAO.mappers.UserRoleMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcUserRoleDao implements DaoUserRole {
    private final JdbcTemplate tem;
    private UserRoleMapper mapper = new UserRoleMapper();
    public JdbcUserRoleDao(JdbcTemplate tem){
        this.tem = tem;
    }

    @Override
    public List<String> findRoleNamesByUserId(long userId) {
        String sql = """
            SELECT r.role_name
            FROM user_role ur
            JOIN roles r ON r.role_id = ur.role_id
            WHERE ur.user_id = ?;
            """;
        try {
            return tem.queryForList(sql, String.class, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    @Override
    public void createNewUserRole(long userId, long roleId) {
        String sql = """
                INSERT INTO user_role(user_id, role_id)
                VALUES (?,3);""";
        try{
            int rows = tem.update(sql, userId);
            if (rows != 1) {
                throw new DaoException("Failed to create user_role row, rows affected: " + rows);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    @Override
    public void updateNewUserRole(long userId) {
        //TODO
    }

    @Override
    public void deleteUserRole(long id) {
        //TODO
    }

}

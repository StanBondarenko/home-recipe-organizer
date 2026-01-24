package com.stanbond.homerecipeorganizer.DAO.mappers;
import com.stanbond.homerecipeorganizer.DAO.entites.UserFavorite;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFavoriteRowMapper implements RowMapper<UserFavorite> {

    @Override
    public UserFavorite mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserFavorite uf = new UserFavorite();
        uf.setUserId(rs.getLong("user_id"));
        uf.setRecId(rs.getLong("rec_id"));
        return uf;
    }
}
package com.stanbond.homerecipeorganizer.DAO.jdbc;

import com.stanbond.homerecipeorganizer.DAO.entites.UserFavorite;
import com.stanbond.homerecipeorganizer.DAO.interfaces.UserFavoriteDao;
import com.stanbond.homerecipeorganizer.DAO.mappers.UserFavoriteRowMapper;
import com.stanbond.homerecipeorganizer.exceptions.DaoException;
import com.stanbond.homerecipeorganizer.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserFavoriteDao implements UserFavoriteDao {

    private final JdbcTemplate template;
    private final UserFavoriteRowMapper mapper = new UserFavoriteRowMapper();

    public JdbcUserFavoriteDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<UserFavorite> getAllByUserId(long userId) {
        String sql = """
                SELECT user_id, rec_id
                FROM user_favorite
                WHERE user_id = ?
                ORDER BY rec_id
                """;
        try {
            return template.query(sql, mapper, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        }
    }

    @Override
    public Optional<List<UserFavorite>> getByRecId(long userId,long recId) {
        String sql = """
                SELECT user_id, rec_id
                FROM user_favorite
                WHERE user_id = ? AND rec_id = ?""";
        try{
            List<UserFavorite> result = template.query(sql,mapper,userId, recId);
            return Optional.of(result);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public void add(long userId, long recId) {
        String sql = """
                INSERT INTO user_favorite(user_id, rec_id)
                VALUES (?, ?)
                """;
        try {
            template.update(sql, userId, recId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data problems", e);
        }
    }

    @Override
    public void remove(long userId, long recId) {
        String sql = """
                DELETE FROM user_favorite
                WHERE user_id = ? AND rec_id = ?
                """;
        try {
            int rows = template.update(sql, userId, recId);
            if (rows == 0) {
                throw new NotFoundException("Favorite not found. userId=" + userId + ", recId=" + recId);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("No connection to base", e);
        }
    }
}
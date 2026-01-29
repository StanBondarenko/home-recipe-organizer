package com.stanbond.homerecipeorganizer.DAO.jdbc;

import com.stanbond.homerecipeorganizer.DAO.entites.DishIng;
import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoDishIng;
import com.stanbond.homerecipeorganizer.DAO.mappers.DishIngMapper;
import com.stanbond.homerecipeorganizer.exceptions.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcDishIngDao implements DaoDishIng {
    private final JdbcTemplate template;
    private DishIngMapper mapper = new DishIngMapper();

    public JdbcDishIngDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<List<DishIng>> getByRecId(long recId) {
        String sql = """
                SELECT i.ing_name, ri.amount, u.code
                FROM ingredient i
                JOIN recipe_ingredient ri USING(ing_id)
                JOIN unit u USING(unit_id)
                WHERE rec_id = ?
                ORDER BY i.ing_id;""";
        try{
            List<DishIng> result = template.query(sql,mapper,recId);
            return Optional.of(result);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("No connection to database",e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Bad Data",e );
        }
    }
}

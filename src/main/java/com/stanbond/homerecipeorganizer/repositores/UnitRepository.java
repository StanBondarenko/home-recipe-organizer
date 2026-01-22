package com.stanbond.homerecipeorganizer.repositores;

import com.stanbond.homerecipeorganizer.DAO.entites.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Long> {
    List<Unit> findAll();
    Optional<Unit> findByCode(String code);
}

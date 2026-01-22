package com.stanbond.homerecipeorganizer.repositores;

import com.stanbond.homerecipeorganizer.DAO.entites.DishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishTypeRepository extends JpaRepository<DishType, Long> {
    List<DishType> findAll();
}

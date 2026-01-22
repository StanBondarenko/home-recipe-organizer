package com.stanbond.homerecipeorganizer.DAO.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="unit")
@Data
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="unit_id")
    private long unitId;
    @Column(nullable = false,name = "code", unique = true)
    private String code;
    @Column(nullable = false,name = "unit_name", unique = true)
    private String unitName;
    @Column(nullable = false,name = "dimension")
    private String dimension;
    @Column(nullable = false,name = "to_base")
    private double toBase;
}

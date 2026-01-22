package com.stanbond.homerecipeorganizer.DAO.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dish_type")
@Data
@NoArgsConstructor
public class DishType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeId;
    @Column(nullable = false, name="type_name",unique = true)
    private String typeName;
}

package com.stanbond.homerecipeorganizer.controllers;

import com.stanbond.homerecipeorganizer.DAO.entites.DishIng;
import com.stanbond.homerecipeorganizer.service.DishIngService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/{recId}/ingredients")
public class DishIngController {
    private final DishIngService service;

    public DishIngController(DishIngService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<DishIng>> getIngByRecId(@PathVariable long recId){
        return ResponseEntity.ok(service.getIngByRecId(recId));
    }
}

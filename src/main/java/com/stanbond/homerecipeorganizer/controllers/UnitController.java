package com.stanbond.homerecipeorganizer.controllers;

import com.stanbond.homerecipeorganizer.DAO.entites.Unit;
import com.stanbond.homerecipeorganizer.service.UnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unit")
public class UnitController {
    private final UnitService service;
    public UnitController (UnitService service){this.service = service;}
    @GetMapping()
    public ResponseEntity<List<Unit>> find(@RequestParam(name="code", required = false,defaultValue = "") String code){
        if (code.isEmpty()){
            return ResponseEntity.ok(service.getAllUnits());
        }else {
            return ResponseEntity.ok((List.of(service.getByCode(code))));
        }
    }
    @GetMapping("/base/{id}")
    public ResponseEntity<Double> getToBaseById(@PathVariable Long id){
        return ResponseEntity.ok(service.getToBaseById(id));
    }
}

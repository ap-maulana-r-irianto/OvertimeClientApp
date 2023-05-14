package com.metrodata.clientapp.controllers.rest;


import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.clientapp.models.Type;
import com.metrodata.clientapp.services.TypeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/type")
@AllArgsConstructor
public class ApiTypeController {

    private TypeService typeService;

    @GetMapping
    public List<Type> getAll(){
        return typeService.getAll();
    }

    @GetMapping("/{id}")
    public Type getById(@PathVariable int id){
        return typeService.getById(id);
    }

    @PostMapping
    public Type create(@RequestBody Type type){
        return typeService.create(type);
    }

    @PutMapping("/{id}")
    public Type update(@PathVariable int id, @RequestBody Type type){
        return typeService.update(id, type);
    }

    @DeleteMapping("/{id}")
    public Type create(@PathVariable int id){
        return typeService.delete(id);
    }
    
}
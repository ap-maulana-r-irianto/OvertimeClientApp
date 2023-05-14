package com.metrodata.clientapp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.clientapp.models.EmployeeProject;
import com.metrodata.clientapp.services.EmployeeProjectService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/employeeproject")
@AllArgsConstructor
public class ApiEmployeeProjectController {

    private EmployeeProjectService employeeProjectService;

    @GetMapping
    public List<EmployeeProject> getAll(){
        return employeeProjectService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeProject getById(@PathVariable int id){
        return employeeProjectService.getById(id);
    }

    @PostMapping
    public EmployeeProject create(@RequestBody EmployeeProject employeeProject){
        return employeeProjectService.create(employeeProject);
    }

    @PutMapping("/{id}")
    public EmployeeProject update(@PathVariable int id, @RequestBody EmployeeProject employeeProject){
        return employeeProjectService.update(id,employeeProject);
    }

    @DeleteMapping("/{id}")
    public EmployeeProject create(@PathVariable int id){
        return employeeProjectService.delete(id);
    }
    
}

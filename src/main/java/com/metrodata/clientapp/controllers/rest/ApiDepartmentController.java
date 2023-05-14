package com.metrodata.clientapp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.clientapp.models.Department;
import com.metrodata.clientapp.models.dto.requests.DepartmentRequest;
import com.metrodata.clientapp.services.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/department")
@AllArgsConstructor
public class ApiDepartmentController {

    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAll(){
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable int id){
        return departmentService.getById(id);
    }

    @PostMapping
    public Department create(@RequestBody DepartmentRequest departmentRequest){
        return departmentService.create(departmentRequest);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable int id, @RequestBody Department department){
        return departmentService.update(id,department);
    }

    @DeleteMapping("/{id}")
    public Department create(@PathVariable int id){
        return departmentService.delete(id);
    }
    
}

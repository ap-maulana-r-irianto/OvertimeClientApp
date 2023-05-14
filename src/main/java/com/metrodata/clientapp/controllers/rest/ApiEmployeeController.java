package com.metrodata.clientapp.controllers.rest;


import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.Employee;
import com.metrodata.clientapp.models.dto.requests.UserRequest;
import com.metrodata.clientapp.services.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class ApiEmployeeController {

    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id){
        return employeeService.getById(id);
    }

    @PostMapping
    public Employee create(@RequestBody UserRequest userRequest){
        return employeeService.create(userRequest);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee employee){
        return employeeService.update(id,employee);
    }

    @DeleteMapping("/{id}")
    public Employee create(@PathVariable int id){
        return employeeService.delete(id);
    }
    
}

package com.metrodata.clientapp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.clientapp.models.Project;
import com.metrodata.clientapp.services.ProjectService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/project")
@AllArgsConstructor
public class ApiProjectController {

    private ProjectService projectService;

    @GetMapping
    public List<Project> getAll(){
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public Project getById(@PathVariable int id){
        return projectService.getById(id);
    }

    @PostMapping
    public Project create(@RequestBody Project project){
        return projectService.create(project);
    }

    @PutMapping("/{id}")
    public Project update(@PathVariable int id, @RequestBody Project project){
        return projectService.update(id,project);
    }

    @DeleteMapping("/{id}")
    public Project create(@PathVariable int id){
        return projectService.delete(id);
    }
    
}

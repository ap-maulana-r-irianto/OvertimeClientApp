package com.metrodata.clientapp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.clientapp.models.Role;
import com.metrodata.clientapp.services.RoleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/role")
@AllArgsConstructor
public class ApiRoleController {
    
    private RoleService roleService;

    @GetMapping
    public List<Role> getAll(){
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable int id){
        return roleService.getById(id);
    }

    @PostMapping
    public Role create(@RequestBody Role role){
        return roleService.create(role);
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable int id, @RequestBody Role role){
        return roleService.update(id, role);
    }

    @DeleteMapping("/{id}")
    public Role create(@PathVariable int id){
        return roleService.delete(id);
    }
}

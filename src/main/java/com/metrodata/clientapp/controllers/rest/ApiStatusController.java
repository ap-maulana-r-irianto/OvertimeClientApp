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

import com.metrodata.clientapp.models.Status;
import com.metrodata.clientapp.services.StatusService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/status")
@AllArgsConstructor
public class ApiStatusController {
    
    private StatusService statusService;

    @GetMapping
    public List<Status> getAll(){
        return statusService.getAll();
    }

    @GetMapping("/{id}")
    public Status getById(@PathVariable int id){
        return statusService.getById(id);
    }

    @PostMapping
    public Status create(@RequestBody Status status){
        return statusService.create(status);
    }

    @PutMapping("/{id}")
    public Status update(@PathVariable int id, @RequestBody Status status){
        return statusService.update(id, status);
    }

    @DeleteMapping("/{id}")
    public Status create(@PathVariable int id){
        return statusService.delete(id);
    }
}

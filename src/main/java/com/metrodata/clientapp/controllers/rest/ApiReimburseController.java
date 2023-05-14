package com.metrodata.clientapp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.clientapp.models.Reimburse;
import com.metrodata.clientapp.models.dto.requests.ReimburseRequest;
import com.metrodata.clientapp.services.ReimburseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/reimburse")
@AllArgsConstructor
public class ApiReimburseController {

    private ReimburseService reimburseService;

    @GetMapping
    public List<Reimburse> getAll(){
        return reimburseService.getAll();
    }

    @GetMapping("/{id}")
    public Reimburse getById(@PathVariable int id){
        return reimburseService.getById(id);
    }

    @PostMapping
    public Reimburse create(@RequestBody ReimburseRequest reimburseRequest){
        return reimburseService.create(reimburseRequest);
    }

    @PutMapping("/{id}")
    public Reimburse update(@PathVariable int id, @RequestBody Reimburse reimburse){
        return reimburseService.update(id,reimburse);
    }

    @DeleteMapping("/{id}")
    public Reimburse create(@PathVariable int id){
        return reimburseService.delete(id);
    }
    
}
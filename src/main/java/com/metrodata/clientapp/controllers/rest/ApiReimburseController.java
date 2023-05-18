package com.metrodata.clientapp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public Reimburse create(ReimburseRequest reimburseRequest, @RequestParam("file") MultipartFile file){
        return reimburseService.create(reimburseRequest, file);
    }

    @PutMapping("/{id}")
    public Reimburse update(@PathVariable int id, @RequestBody Reimburse reimburse){
        return reimburseService.update(id, reimburse);
    }

    @PutMapping("/approv/manager/{id}")
    public Reimburse approvManager(@PathVariable int id){
        return reimburseService.approvManager(id);
    }

    @PutMapping("/reject/manager/{id}")
    public Reimburse rejectManager(@PathVariable int id, @RequestBody String description){
        return reimburseService.rejectManager(id, description);
    }

    @PutMapping("/approv/hr/{id}")
    public Reimburse approvHr(@PathVariable int id){
        return reimburseService.approvHr(id);
    }

    @PutMapping("/reject/hr/{id}")
    public Reimburse rejectHr(@PathVariable int id, @RequestBody String description){
        return reimburseService.rejectHr(id, description);
    }

    @PutMapping("/paid/{id}")
    public Reimburse paid(@PathVariable int id){
        return reimburseService.paid(id);
    }

    @DeleteMapping("/{id}")
    public Reimburse create(@PathVariable int id){
        return reimburseService.delete(id);
    }
    
}
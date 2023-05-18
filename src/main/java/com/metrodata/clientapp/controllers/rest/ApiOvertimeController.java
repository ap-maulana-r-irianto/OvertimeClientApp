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

import com.metrodata.clientapp.models.Overtime;
import com.metrodata.clientapp.models.dto.requests.OvertimeRequest;
import com.metrodata.clientapp.services.OvertimeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/overtime")
@AllArgsConstructor
public class ApiOvertimeController {

    private OvertimeService overtimeService;

    @GetMapping
    public List<Overtime> getAll(){
        return overtimeService.getAll();
    }

    @GetMapping("/{id}")
    public Overtime getById(@PathVariable int id){
        return overtimeService.getById(id);
    }

    @PostMapping
    public Overtime create(@RequestBody OvertimeRequest overtimeRequest){
        return overtimeService.create(overtimeRequest);
    }

    @PutMapping("/{id}")
    public Overtime update(@PathVariable int id, @RequestBody Overtime overtime){
        return overtimeService.update(id, overtime);
    }

    @PutMapping("/approv/manager/{id}")
    public Overtime approvManager(@PathVariable int id){
        return overtimeService.approvManager(id);
    }

    @PutMapping("/reject/manager/{id}")
    public Overtime rejectManager(@PathVariable int id, @RequestBody String description){
        return overtimeService.rejectManager(id, description);
    }

    @PutMapping("/approv/hr/{id}")
    public Overtime approvHr(@PathVariable int id){
        return overtimeService.approvHr(id);
    }

    @PutMapping("/reject/hr/{id}")
    public Overtime rejectHr(@PathVariable int id, @RequestBody String description){
        return overtimeService.rejectHr(id, description);
    }

    @PutMapping("/paid/{id}")
    public Overtime paid(@PathVariable int id){
        return overtimeService.paid(id);
    }

    @DeleteMapping("/{id}")
    public Overtime create(@PathVariable int id){
        return overtimeService.delete(id);
    }
    
}

package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metrodata.clientapp.models.Status;
import com.metrodata.clientapp.services.StatusService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/status")
@AllArgsConstructor
@PreAuthorize("hasRole('HRD')")
public class StatusController {
    
    private StatusService statusService;

    @GetMapping
    public String index(Model model) {
        return "status/index";
    }

    @PostMapping
    public String create(Status status) {
        statusService.create(status);
        return "redirect:/status";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, Status status) {
        statusService.update(id, status);
        return "redirect:/status";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        statusService.delete(id);
        return "redirect:/status";
    }
}

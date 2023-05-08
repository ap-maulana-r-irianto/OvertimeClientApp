package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.Type;
import com.metrodata.clientapp.services.TypeService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/type")
@AllArgsConstructor
@PreAuthorize("hasRole('HRD')")
public class TypeController {
    
    private TypeService typeService;

    @GetMapping
    public String index(Model model) {
        List<Type> types = typeService.getAll();
        model.addAttribute("types", types);
        return "type/index";
    }

    @PostMapping
    public String create(Type type) {
        typeService.create(type);
        return "redirect:/type";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, Type type) {
        typeService.update(id, type);
        return "redirect:/type";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        typeService.delete(id);
        return "redirect:/type";
    }
}

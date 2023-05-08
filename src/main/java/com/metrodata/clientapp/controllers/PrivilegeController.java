package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.Privilege;
import com.metrodata.clientapp.services.PrivilegeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/privilege")
@PreAuthorize("hasRole('HRD')")
public class PrivilegeController {

    private PrivilegeService privilegeService;

    @GetMapping
    public String index(Model model) {
        List<Privilege> privileges = privilegeService.getAll();
        model.addAttribute("privileges", privileges);
        return "privilege/index";
    }

    @GetMapping("/create")
    public String createView(Privilege privilege, Model model) {
        return "privilege/create-form";
    }

    @PostMapping
    public String create(Privilege privilege) {
        privilegeService.create(privilege);
        return "redirect:/privilege";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Model model) {
        model.addAttribute("privilege", privilegeService.getById(id));
        return "privilege/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, Privilege privilege){
        privilegeService.update(id, privilege);
        return "redirect:/privilege";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        privilegeService.delete(id);
        return "redirect:/privilege";
    }

    
}

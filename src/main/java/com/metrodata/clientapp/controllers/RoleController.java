package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.Role;
import com.metrodata.clientapp.services.RoleService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/role")
@AllArgsConstructor
@PreAuthorize("hasRole('HRD')")
public class RoleController {

    private RoleService roleService;

    @GetMapping
    public String index(Model model) {
        List<Role> roles = roleService.getAll();
        model.addAttribute("roles", roles);
        return "role/index";
    }

    @PostMapping
    public String create(Role role) {
        roleService.create(role);
        return "redirect:/role";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, Role role) {
        roleService.update(id, role);
        return "redirect:/role";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        roleService.delete(id);
        return "redirect:/role";
    }
    
}

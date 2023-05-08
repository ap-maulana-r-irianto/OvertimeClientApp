package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.Department;
import com.metrodata.clientapp.models.dto.requests.DepartmentRequest;
import com.metrodata.clientapp.services.DepartmentService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/department")
@PreAuthorize("hasRole('HRD')")
public class DepartmentController {

    private DepartmentService departmentService;

    @GetMapping
    public String index(Model model) {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "department/index";
    }

    @GetMapping("/create")
    public String createView(DepartmentRequest departmentRequest, Model model) {
        return "department/create-form";
    }

    @PostMapping
    public String create(DepartmentRequest departmentRequest) {
        departmentService.create(departmentRequest);
        return "redirect:/department";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        return "department/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, Department department){
        departmentService.update(id, department);
        return "redirect:/department";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        departmentService.delete(id);
        return "redirect:/department";
    }

    
}

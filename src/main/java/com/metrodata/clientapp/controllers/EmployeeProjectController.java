package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.EmployeeProject;
import com.metrodata.clientapp.services.EmployeeProjectService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/employeeproject")
@PreAuthorize("hasRole('HRD')")
public class EmployeeProjectController {
    
    private EmployeeProjectService employeeProjectService;

    @GetMapping("/hr")
    public String indexHr(Model model) {
        List<EmployeeProject> employeeprojects = employeeProjectService.getAll();
        model.addAttribute("employeeprojects", employeeprojects);
        return "hr/employee_project";
    }

    @GetMapping("/create")
    public String createViewHr(EmployeeProject employeeProject, Model model) {
        return "hr/employee_project";
    }

    @PostMapping
    public String createHr(EmployeeProject employeeProject) {
        employeeProjectService.create(employeeProject);
        return "redirect:/hr";
    }

    @GetMapping("/update/{id}")
    public String updateViewHr(@PathVariable int id, Model model) {
        model.addAttribute("employeeProject", employeeProjectService.getById(id));
        return "hr/employee_project";
    }

    @PutMapping("/{id}")
    public String updateHr(@PathVariable int id, EmployeeProject employeeProject){
        employeeProjectService.update(id, employeeProject);
        return "redirect:/hr";
    }

    @DeleteMapping("/{id}")
    public String deleteHr(@PathVariable int id){
        employeeProjectService.delete(id);
        return "redirect:/hr";
    }
    @GetMapping("/executive")
    public String indexExecutive(Model model) {
        List<EmployeeProject> employeeprojects = employeeProjectService.getAll();
        model.addAttribute("employeeprojects", employeeprojects);
        return "executive/employee_project";
    }

    @GetMapping("/executive/create")
    public String createViewExecutive(EmployeeProject employeeProject, Model model) {
        return "executive/employee_project";
    }

    @PostMapping("/executive")
    public String createExecutive(EmployeeProject employeeProject) {
        employeeProjectService.create(employeeProject);
        return "redirect:/Executive";
    }

    @GetMapping("/executive/update/{id}")
    public String updateViewExecutive(@PathVariable int id, Model model) {
        model.addAttribute("employeeProject", employeeProjectService.getById(id));
        return "executive/employee_project";
    }

    @PutMapping("/executive/{id}")
    public String updateExecutive(@PathVariable int id, EmployeeProject employeeProject){
        employeeProjectService.update(id, employeeProject);
        return "redirect:/executive";
    }

    @DeleteMapping("/executive/{id}")
    public String deleteExecutive(@PathVariable int id){
        employeeProjectService.delete(id);
        return "redirect:/executive";
    }
    

}
package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.Project;
import com.metrodata.clientapp.services.ProjectService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/project")
@PreAuthorize("hasRole('HRD')")
public class ProjectController {
    
    private ProjectService projectService;

    @GetMapping("/hr")
    public String indexHr(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "hr/project";
    }

    @GetMapping("/create")
    public String createViewHr(Project project, Model model) {
        return "hr/project";
    }

    @PostMapping
    public String createHr(Project project) {
        projectService.create(project);
        return "redirect:/hr";
    }

    @GetMapping("/update/{id}")
    public String updateViewHr(@PathVariable int id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        return "hr/project";
    }

    @PutMapping("/{id}")
    public String updateHr(@PathVariable int id, Project project){
        projectService.update(id, project);
        return "redirect:/hr";
    }

    @DeleteMapping("/{id}")
    public String deleteHr(@PathVariable int id){
        projectService.delete(id);
        return "redirect:/hr";
    }

    @GetMapping("/executive")
    public String indexExecutive(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "executive/project";
    }

    @GetMapping("/executive/create")
    public String createViewExecutive(Project project, Model model) {
        return "executive/project";
    }

    @PostMapping("/executive")
    public String createExecutive(Project project) {
        projectService.create(project);
        return "redirect:/executive";
    }

    @GetMapping("/executive/update/{id}")
    public String updateViewExecutive(@PathVariable int id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        return "executive/project";
    }

    @PutMapping("/executive/{id}")
    public String updateExecutive(@PathVariable int id, Project project){
        projectService.update(id, project);
        return "redirect:/executive";
    }

    @DeleteMapping("/executive/{id}")
    public String deleteExecutive(@PathVariable int id){
        projectService.delete(id);
        return "redirect:/executive";
    }

}

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

    @GetMapping
    public String index(Model model) {
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "project/index";
    }

    @GetMapping("/create")
    public String createView(Project project, Model model) {
        return "project/create-form";
    }

    @PostMapping
    public String create(Project project) {
        projectService.create(project);
        return "redirect:/project";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        return "project/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, Project project){
        projectService.update(id, project);
        return "redirect:/project";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        projectService.delete(id);
        return "redirect:/project";
    }

}

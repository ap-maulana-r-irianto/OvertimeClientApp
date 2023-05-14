package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.Overtime;
import com.metrodata.clientapp.models.dto.requests.OvertimeRequest;
import com.metrodata.clientapp.services.OvertimeService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/overtime")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('HRD','EMPLOYEE','LEADER')")
public class OvertimeController {
    
    private OvertimeService overtimeService;

    @GetMapping("/employee")
    public String indexEmployee (Model model) {
        List<Overtime> overtimes = overtimeService.getAll();
        model.addAttribute("overtimes", overtimes);
        return "employee/overtime";
    }

    @GetMapping("/{id}")
    public String indexIdEmployee (@PathVariable int id, Model model) {
        model.addAttribute("overtime", overtimeService.getById(id));
        return "employee/overtime";
    }

    @GetMapping("/create")
    public String createViewEmployee (OvertimeRequest overtimeRequest) {
        return "employee/overtime";
    }

    @PostMapping
    public String createEmployee(OvertimeRequest overtimeRequest) {
        overtimeService.create(overtimeRequest);
        return "redirect:/overtime";
    }

    @GetMapping("/update/{id}")
    public String updateViewEmployee(@PathVariable int id, Model model) {
        model.addAttribute("overtime", overtimeService.getById(id));
        return "employee/overtime";
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable int id, Overtime overtime) {
        overtimeService.update(id, overtime);
        return "redirect:/employee";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        overtimeService.delete(id);
        return "redirect:/employee";
    }


    @GetMapping("/hr")
    public String indexHr(Model model) {
        List<Overtime> overtimes = overtimeService.getAll();
        model.addAttribute("overtimes", overtimes);
        return "hr/overtime";
    }

    @GetMapping("/hr/{id}")
    public String indexIdHr(@PathVariable int id, Model model) {
        model.addAttribute("overtime", overtimeService.getById(id));
        return "hr/overtime";
    }

    @GetMapping("/hr/create")
    public String createViewHr(OvertimeRequest overtimeRequest) {
        return "hr/overtime";
    }

    @PostMapping("/hr")
    public String createHr(OvertimeRequest overtimeRequest) {
        overtimeService.create(overtimeRequest);
        return "redirect:/hr";
    }

    @GetMapping("/hr/update/{id}")
    public String updateViewHr(@PathVariable int id, Model model) {
        model.addAttribute("overtime", overtimeService.getById(id));
        return "hr/overtime";
    }

    @PutMapping("/hr/{id}")
    public String updateHr(@PathVariable int id, Overtime overtime) {
        overtimeService.update(id, overtime);
        return "redirect:/hr";
    }

    @DeleteMapping("/hr/{id}")
    public String deleteHr(@PathVariable int id) {
        overtimeService.delete(id);
        return "redirect:/hr";
    }

    @GetMapping("/executive")
    public String indexExecutive(Model model) {
        List<Overtime> overtimes = overtimeService.getAll();
        model.addAttribute("overtimes", overtimes);
        return "executive/overtime";
    }

    @GetMapping("/executive/{id}")
    public String indexIdExecutive(@PathVariable int id, Model model) {
        model.addAttribute("overtime", overtimeService.getById(id));
        return "executive/overtime";
    }

    @GetMapping("/executive/create")
    public String createViewExecutive(OvertimeRequest overtimeRequest) {
        return "executive/overtime";
    }

    @PostMapping("/executive")
    public String createExecutive(OvertimeRequest overtimeRequest) {
        overtimeService.create(overtimeRequest);
        return "redirect:/executive";
    }

    @GetMapping("/executive/update/{id}")
    public String updateViewExecutive(@PathVariable int id, Model model) {
        model.addAttribute("overtime", overtimeService.getById(id));
        return "executive/overtime";
    }

    @PutMapping("/executive/{id}")
    public String updateExecutive(@PathVariable int id, Overtime overtime) {
        overtimeService.update(id, overtime);
        return "redirect:/executive";
    }

    @DeleteMapping("/executive/{id}")
    public String deleteExecutive(@PathVariable int id) {
        overtimeService.delete(id);
        return "redirect:/executive";
    }

}



    

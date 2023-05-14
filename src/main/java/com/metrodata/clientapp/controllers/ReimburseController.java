package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.Reimburse;
import com.metrodata.clientapp.models.dto.requests.ReimburseRequest;
import com.metrodata.clientapp.services.ReimburseService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/reimburse")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('HRD','EMPLOYEE','LEADER')")
public class ReimburseController {

    private ReimburseService reimburseService;

    @GetMapping("/employee")
    public String indexEmployee(Model model) {
        List<Reimburse> reimburses = reimburseService.getAll();
        model.addAttribute("reimburses", reimburses);
        return "employee/reimburse";
    }

    @GetMapping("/{id}")
    public String indexIdEmployee(@PathVariable int id, Model model) {
        model.addAttribute("reimburse", reimburseService.getById(id));
        return "employee/reimburse";
    }

    @GetMapping("/create")
    public String createViewEmployee(ReimburseRequest reimburseRequest) {
        return "employee/reimburse";
    }

    @PostMapping
    public String createEmployee(ReimburseRequest reimburseRequest) {
        reimburseService.create(reimburseRequest);
        return "redirect:/employee";
    }

    @GetMapping("/update/{id}")
    public String updateViewEmployee(@PathVariable int id, Model model) {
        model.addAttribute("reimburse", reimburseService.getById(id));
        return "employee/reimburse";
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable int id, Reimburse reimburse) {
        reimburseService.update(id, reimburse);
        return "redirect:/employee";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        reimburseService.delete(id);
        return "redirect:/employee";
    }

    @GetMapping("/hr")
    public String indexHr(Model model) {
        List<Reimburse> reimburses = reimburseService.getAll();
        model.addAttribute("reimburses", reimburses);
        return "hr/reimburse";
    }

    @GetMapping("/hr/{id}")
    public String indexIdHr(@PathVariable int id, Model model) {
        model.addAttribute("reimburse", reimburseService.getById(id));
        return "hr/reimburse";
    }

    @GetMapping("/hr/create")
    public String createViewHr(ReimburseRequest reimburseRequest) {
        return "hr/reimburse";
    }

    @PostMapping("/hr")
    public String createHr(ReimburseRequest reimburseRequest) {
        reimburseService.create(reimburseRequest);
        return "redirect:/hr";
    }

    @GetMapping("/hr/update/{id}")
    public String updateViewHr(@PathVariable int id, Model model) {
        model.addAttribute("reimburse", reimburseService.getById(id));
        return "hr/reimburse";
    }

    @PutMapping("/hr/{id}")
    public String updateHr(@PathVariable int id, Reimburse reimburse) {
        reimburseService.update(id, reimburse);
        return "redirect:/hr";
    }

    @DeleteMapping("/hr/{id}")
    public String deleteHr(@PathVariable int id) {
        reimburseService.delete(id);
        return "redirect:/hr";
    }

    @GetMapping("/hr/executive")
    public String indexExecutiveEmployee(Model model) {
        List<Reimburse> reimburses = reimburseService.getAll();
        model.addAttribute("reimburses", reimburses);
        return "executive/reimburse_employee";
    }
    @GetMapping("/executive")
    public String indexExecutive(Model model) {
        List<Reimburse> reimburses = reimburseService.getAll();
        model.addAttribute("reimburses", reimburses);
        return "executive/reimburse";
    }

    @GetMapping("/executive/{id}")
    public String indexIdExecutive(@PathVariable int id, Model model) {
        model.addAttribute("reimburse", reimburseService.getById(id));
        return "executive/reimburse";
    }

    @GetMapping("/executive/create")
    public String createViewExecutive(ReimburseRequest reimburseRequest) {
        return "executive/reimburse";
    }

    @PostMapping("/executive")
    public String createExecutive(ReimburseRequest reimburseRequest) {
        reimburseService.create(reimburseRequest);
        return "redirect:/executive";
    }

    @GetMapping("/executive/update/{id}")
    public String updateViewExecutive(@PathVariable int id, Model model) {
        model.addAttribute("reimburse", reimburseService.getById(id));
        return "executive/reimburse";
    }

    @PutMapping("/executive/{id}")
    public String updateExecutive(@PathVariable int id, Reimburse reimburse) {
        reimburseService.update(id, reimburse);
        return "redirect:/executive";
    }

    @DeleteMapping("/executive/{id}")
    public String deleteExecutive(@PathVariable int id) {
        reimburseService.delete(id);
        return "redirect:/executive";
    }

}

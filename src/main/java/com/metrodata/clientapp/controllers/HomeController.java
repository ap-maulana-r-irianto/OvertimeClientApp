package com.metrodata.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    
    @GetMapping("/homeemployee")
    public String indexEmployee(Model model) {
        return "employee/index";
    }

    @GetMapping("/homeexecutive")
    public String indexExecutive(Model model) {
        return "executive/index";
    }

    @GetMapping("/homehr")
    public String indexHr(Model model) {
        return "hr/index";
    }
}

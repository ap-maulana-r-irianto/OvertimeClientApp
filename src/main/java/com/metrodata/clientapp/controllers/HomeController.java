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
<<<<<<< HEAD
=======
    }

    @GetMapping("/homeexecutive")
    public String indexExecutive(Model model) {
        return "executive/index";
    }

    @GetMapping("/homehr")
    public String indexHr(Model model) {
        return "hr/index";
>>>>>>> c014ff594b934414d4a50a1d70ff3cb8b635e2e9
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
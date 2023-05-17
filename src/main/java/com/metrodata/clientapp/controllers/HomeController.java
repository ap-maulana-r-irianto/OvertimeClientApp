package com.metrodata.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    
    @GetMapping("/home_employee")
    public String indexEmployee(Model model) {
        return "employee/index";
    }
    @GetMapping("/overtime_employee")
    public String overtimeEmployee(Model model) {
        return "employee/overtime";
    }
    @GetMapping("/reimburse_employee")
    public String reimburseEmployee(Model model) {
        return "employee/reimburse";
    }


    @GetMapping("/home_executive")
    public String indexExecutive(Model model) {
        return "executive/index";
    }
    @GetMapping("/overtime_executive")
    public String overtimeExecutive(Model model) {
        return "executive/overtime";
    }
    @GetMapping("/reimburse_executive")
    public String reimburseExecutive(Model model) {
        return "executive/reimburse";
    }
    @GetMapping("/profil_executive")
    public String profilExecutive(Model model) {
        return "executive/profil";
    }
    @GetMapping("/employee_project_executive")
    public String employee_projectExecutive(Model model) {
        return "executive/employee_project";
    }
    @GetMapping("/project_executive")
    public String projectExecutive(Model model) {
        return "executive/project";
    }


    @GetMapping("/home_hr")
    public String indexHr(Model model) {
        return "hr/index";
    }
    @GetMapping("/overtime_hr")
    public String overtimeHr(Model model) {
        return "hr/overtime";
    }
    @GetMapping("/reimburse_hr")
    public String reimburseHr(Model model) {
        return "hr/reimburse";
    }
    @GetMapping("/employee_hr")
    public String employeeHr(Model model) {
        return "hr/employee";
    }
    @GetMapping("/profil_hr")
    public String profilHr(Model model) {
        return "hr/profil";
    }
    @GetMapping("/employee_project_hr")
    public String employee_projectHr(Model model) {
        return "hr/employee_project";
    }
    @GetMapping("/project_hr")
    public String projectHr(Model model) {
        return "hr/project";
    }
    @GetMapping("/add_employee_hr")
    public String add_employeeHr(Model model) {
        return "hr/add_employee";
    }
    @GetMapping("/department_hr")
    public String departmentHr(Model model) {
        return "hr/department";
    }
    @GetMapping("/type_hr")
    public String typeHr(Model model) {
        return "hr/type";
    }
    @GetMapping("/status_hr")
    public String statusHr(Model model) {
        return "hr/status";
    }
}
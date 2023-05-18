package com.metrodata.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metrodata.clientapp.models.dto.requests.LoginRequest;
import com.metrodata.clientapp.services.LoginService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;

    @GetMapping
    public String loginPage(LoginRequest loginRequest) {
        return "auth/login";
    }

    @PostMapping
    public String login(LoginRequest loginRequest) {
        if (!loginService.login(loginRequest)) {
            return "redirect:/login?error=true";
        }
        return "redirect:/home_hr";
    }

    // @PostMapping("/hr")
    // public String loginHr(LoginRequest loginRequest) {
    //     if (!loginService.login(loginRequest)) {
    //         return "redirect:/login?error=true";
    //     }
    //     return "redirect:/home_hr";
    // }

    // @PostMapping("/manager")
    // public String loginManager(LoginRequest loginRequest) {
    //     if (!loginService.login(loginRequest)) {
    //         return "redirect:/login?error=true";
    //     }
    //     return "redirect:/home_executive";
    // }

    // @PostMapping("/employee")
    // public String loginEmployee(LoginRequest loginRequest) {
    //     if (!loginService.login(loginRequest)) {
    //         return "redirect:/login?error=true";
    //     }
    //     return "redirect:/home_employee";
    // }

}

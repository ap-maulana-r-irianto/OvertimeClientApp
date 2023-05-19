package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metrodata.clientapp.models.Role;
import com.metrodata.clientapp.models.User;
import com.metrodata.clientapp.models.dto.requests.LoginRequest;
import com.metrodata.clientapp.services.LoginService;
import com.metrodata.clientapp.services.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;
    private UserService userService;

    @GetMapping
    public String loginPage(LoginRequest loginRequest) {
        return "auth/login";
    }

    @PostMapping
    public String login(LoginRequest loginRequest, Authentication authentication) {
        if (!loginService.login(loginRequest)) {
            return "redirect:/login?error=true";
        }
        User user = userService.getByUsername(loginRequest.getUsername());
        if (user.hasRole("hr")) {
            return "redirect:/home_hr";
        }
        if (user.hasRole("manager")) {
            return "redirect:/home_executive";
        }
        if (user.hasRole("employee")) {
            return "redirect:/home_employee";
        }
        return "redirect:/login?error=true";

    }

}

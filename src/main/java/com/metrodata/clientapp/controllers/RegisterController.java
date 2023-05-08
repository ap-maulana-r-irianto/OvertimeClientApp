package com.metrodata.clientapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metrodata.clientapp.models.dto.requests.UserRequest;
import com.metrodata.clientapp.services.RegisterService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {
    
    private RegisterService registerService;

    @GetMapping
    public String registerPage(UserRequest userRequest){
        return "auth/register";
    }

    @PostMapping
    public String register(UserRequest userRequest){
        registerService.register(userRequest);
        return "redirect:/login";
    }
}

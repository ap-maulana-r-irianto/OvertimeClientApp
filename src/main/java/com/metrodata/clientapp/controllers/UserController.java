package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.Role;
import com.metrodata.clientapp.models.User;
import com.metrodata.clientapp.models.dto.requests.UserRequest;
import com.metrodata.clientapp.services.RegisterService;
import com.metrodata.clientapp.services.RoleService;
import com.metrodata.clientapp.services.UserService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@PreAuthorize("hasRole('HRD')")
public class UserController {

    private UserService userService;
    private RegisterService registerService;
    private RoleService roleService;

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "user/index";
    }

    @GetMapping("/{id}")
    public String indexId(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/detail-form";
    }

    @GetMapping("/create")
    public String createView(UserRequest userRequest) {
        return "user/create-form";
    }

    @PostMapping
    public String create(UserRequest userRequest) {
        registerService.create(userRequest);
        return "redirect:/user";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, User user) {
        userService.update(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/user";
    }

    @GetMapping("/add/{id}")
    public String addRoleView(@PathVariable int id, User user, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getAll());
        return "user/add-role";
    }
    
    @PostMapping("/add/{id}")
    public String addRole(@PathVariable int id, Role role) {
        userService.addRole(id,role);
        return "redirect:/user";
    }

    @GetMapping("/remove/{id}")
    public String removeRoleView(@PathVariable int id, User user, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getAll());
        return "user/remove-role";
    }

    @PostMapping("/remove/{id}")
    public String removeRole(@PathVariable int id, Role role) {
        userService.removeRole(id,role);
        return "redirect:/user";
    }
    
}

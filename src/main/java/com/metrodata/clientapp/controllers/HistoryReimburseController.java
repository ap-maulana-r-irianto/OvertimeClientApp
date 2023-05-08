package com.metrodata.clientapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.metrodata.clientapp.models.HistoryReimburse;
import com.metrodata.clientapp.models.dto.requests.HistoryReimburseRequest;
import com.metrodata.clientapp.services.HistoryReimburseService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/historyreimburse")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('HRD','LEADER')")
public class HistoryReimburseController {

    private HistoryReimburseService historyReimburseService;

    @GetMapping
    public String index(Model model) {
        List<HistoryReimburse> historyReimburses = historyReimburseService.getAll();
        model.addAttribute("historyReimburses", historyReimburses);
        return "historyReimburse/index";
    }

    @GetMapping("/{id}")
    public String indexId(@PathVariable int id, Model model) {
        model.addAttribute("historyReimburse", historyReimburseService.getById(id));
        return "historyReimburse/detail-form";
    }

    @GetMapping("/create")
    public String createView(HistoryReimburseRequest historyReimburseRequest) {
        return "historyReimburse/create-form";
    }

    @PostMapping
    public String create(HistoryReimburseRequest historyReimburseRequest) {
        historyReimburseService.create(historyReimburseRequest);
        return "redirect:/historyReimburse";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Model model) {
        model.addAttribute("historyReimburse", historyReimburseService.getById(id));
        return "historyReimburse/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, HistoryReimburse historyReimburse) {
        historyReimburseService.update(id, historyReimburse);
        return "redirect:/historyReimburse";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        historyReimburseService.delete(id);
        return "redirect:/historyReimburse";
    }
    
}

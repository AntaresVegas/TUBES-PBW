package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Setlist;
import com.example.demo.service.SetlistService;

@Controller
@RequestMapping("/setlist_details")
public class SetlistDetailsController {

    @Autowired
    private SetlistService setlistService;

    @GetMapping("/{setlistId}")
    public String getSetlistDetails(@PathVariable Long setlistId, Model model) {
        // Mendapatkan setlist berdasarkan ID
        Setlist setlist = setlistService.getSetlistById(setlistId);

        // Menambahkan data setlist ke model untuk template
        model.addAttribute("setlist", setlist);

        return "setlist_detail"; // Mengarah ke setlist_detail.html
    }
}


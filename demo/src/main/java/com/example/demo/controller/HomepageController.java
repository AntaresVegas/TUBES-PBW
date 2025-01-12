package com.example.demo.controller;

import com.example.demo.entity.Setlist;
import com.example.demo.service.SetlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomepageController {

    @Autowired
    private SetlistService setlistService;

    @GetMapping("/")
    public String homepage(Model model) {
        // Ambil semua setlist dari database
        List<Setlist> setlists = setlistService.getAllSetlists();
        System.out.println("Setlists: " + setlists);
        // Tambahkan data ke model
        model.addAttribute("setlists", setlists);

        return "homepage"; // Nama file HTML adalah homepage.html
    }
}

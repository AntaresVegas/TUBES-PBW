package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {

    @GetMapping("/setlist")
    public String setlistPage() {
        return "setlist"; // Mengarah ke setlist.html di folder templates/page
    }

    @GetMapping("/artists")
    public String artistsPage() {
        return "top-artists"; // Mengarah ke top-artists.html di folder templates/page
    }

    @GetMapping("/festivals")
    public String festivalsPage() {
        return "festival"; // Mengarah ke festival.html di folder templates/page
    }

    @GetMapping("/signin-required")
    public String signinRequiredPage() {
        return "signin-required"; // Mengarah ke festival.html di folder templates/page
    }
}

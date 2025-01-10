package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.AdminService;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private MemberService memberService;
    private AdminService adminService;


    @GetMapping("/login")
    public String showLoginPage() {
        return "signin"; // Nama file HTML untuk login
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/register")
    public String registerMember(@RequestParam("fullName") String fullName,
                              @RequestParam("username") String username,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password,
                              Model model) {
    try {
        memberService.registerMember(fullName, username, email, password);
        return "redirect:/login"; // Redirect ke halaman login setelah berhasil
    } catch (RuntimeException e) {
        model.addAttribute("error", e.getMessage());
        return "register"; // Kembali ke halaman register jika ada error
    }
}


    
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Member;
import com.example.demo.service.AuthService;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller // Menggunakan @Controller karena Anda mengembalikan nama view (HTML)
public class AuthController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private AuthService authService;

    // Rute untuk halaman login
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error); // Menambahkan pesan error ke model jika ada
        }
        return "signin"; // Nama file HTML untuk login
    }

    // Rute untuk register page
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("member", new Member());
        return "register"; // Nama file HTML untuk halaman register
    }

    // Rute untuk logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Hapus session pengguna
        session.invalidate();
        // Redirect ke halaman utama
        return "redirect:/";
    }

    // Rute untuk registrasi
    @PostMapping("/register")
    public String registerMember(@RequestParam("fullName") String fullName,
                                  @RequestParam("username") String username,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  Model model) {
        try {
            // Lakukan registrasi
            memberService.registerMember(fullName, username, email, password);

            // Redirect ke halaman login setelah berhasil
            return "redirect:/login";
        } catch (RuntimeException e) {
            // Tangani error seperti email atau username sudah terdaftar
            model.addAttribute("error", e.getMessage());
            return "register"; // Tetap di halaman register jika ada error
        }
    }

    // Rute untuk login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            // Proses login
            Object user = authService.login(email, password);

            if (user instanceof Admin) {
                // Jika admin, arahkan ke halaman admin
                session.setAttribute("admin", user);
                return "redirect:/admin";
            } else if (user instanceof Member) {
                // Jika member, arahkan ke halaman member
                session.setAttribute("member", user);
                return "redirect:/members/member-setlist-page";
            }
        } catch (RuntimeException e) {
            // Jika login gagal, kembali ke halaman login dengan pesan error
            return "redirect:/login?error=" + e.getMessage();
        }

        return "redirect:/login"; // Default fallback
    }
}

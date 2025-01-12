package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MemberService memberService;

    // Halaman utama admin (Dashboard)
    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("message", "Selamat datang di Dashboard Admin!");
        return "admin-dashboard"; // File: admin-dashboard.html
    }

    // Halaman Manage Members
    @GetMapping("/members")
    public String getAllMembers(Model model) {
        try {
            List<Member> members = memberService.getAllMembers();
            model.addAttribute("members", members);
            model.addAttribute("newMember", new Member());
        } catch (Exception e) {
            model.addAttribute("error", "Gagal memuat data anggota: " + e.getMessage());
        }
        return "admin-manage-user"; // File: admin-manage-user.html
    }

    // Halaman Add Artist
    @GetMapping("/admin-add-artist")
    public String addArtistPage() {
        return "admin-add-artist"; // File: admin-add-artist.html
    }

    // Halaman Add Show
    @GetMapping("/admin-add-show")
    public String addShowPage() {
        return "admin-add-show"; // File: admin-add-show.html
    }

    // Halaman Add Setlist
    @GetMapping("/admin-add-setlist")
    public String addSetlistPage() {
        return "admin-add-setlist"; // File: admin-add-setlist.html
    }

    // Halaman Artist Management
    @GetMapping("/admin-artists")
    public String manageArtists() {
        return "admin-artist"; // File: admin-artist.html
    }

    // Halaman Festival Management
    @GetMapping("/admin-festivals")
    public String manageFestivals() {
        return "admin-festival"; // File: admin-festival.html
    }

    // Halaman Generate Reports
    @GetMapping("/admin-generate-report")
    public String generateReportPage() {
        return "admin-generate-report"; // File: admin-generate-report.html
    }

    // Halaman Function Management (Optional)
    @GetMapping("/admin-member-function")
    public String memberFunctionPage() {
        return "admin-member-function"; // File: admin-member-function.html
    }

    // Halaman Setlist Management
    @GetMapping("/admin-setlist")
    public String manageSetlist() {
        return "admin-setlist"; // File: admin-setlist.html
    }

    // Halaman Edit Member
    @GetMapping("/admin-edit-member")
    public String editMemberPage(Model model) {
        model.addAttribute("member", new Member());
        return "admin-edit-member"; // File: admin-edit-member.html
    }

    // Menambahkan anggota baru
    @PostMapping("/members/add")
    public String addMember(@ModelAttribute("newMember") Member member, Model model) {
        try {
            memberService.addMember(member);
        } catch (Exception e) {
            model.addAttribute("error", "Gagal menambahkan anggota: " + e.getMessage());
        }
        return "redirect:/admin/members";
    }

    // Memperbarui data anggota
    @PostMapping("/members/update/{id}")
    public String updateMember(@PathVariable Long id, @ModelAttribute("updatedMember") Member member, Model model) {
        try {
            memberService.updateMember(id, member);
        } catch (Exception e) {
            model.addAttribute("error", "Gagal memperbarui anggota: " + e.getMessage());
        }
        return "redirect:/admin/members";
    }

    // Menghapus anggota berdasarkan ID
    @GetMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable Long id, Model model) {
        try {
            memberService.deleteMember(id);
        } catch (Exception e) {
            model.addAttribute("error", "Gagal menghapus anggota: " + e.getMessage());
        }
        return "redirect:/admin/members";
    }
}

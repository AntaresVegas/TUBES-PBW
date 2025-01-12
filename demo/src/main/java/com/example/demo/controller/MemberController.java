package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Get all members
    @GetMapping
    public String manageMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        model.addAttribute("newMember", new Member());
        return "members"; // Mengarah ke file members.html
    }

    // Add a new member
    @PostMapping("/add")
    public String addMember(@ModelAttribute("newMember") Member member) {
        memberService.addMember(member);
        return "redirect:/members";
    }

    // Update a member
    @PostMapping("/update/{id}")
    public String updateMember(@PathVariable Long id, @ModelAttribute("updatedMember") Member member) {
        memberService.updateMember(id, member);
        return "redirect:/members";
    }

    // Delete a member
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/members";
    }

    // Halaman Member Setlist Page
    @GetMapping("/member-setlist-page")
    public String showMemberSetlistPage() {
        return "member-setlist-page"; // Nama file HTML
    }

    // Halaman Member Artist Page
    @GetMapping("/member-artist-page")
    public String showMemberArtistPage() {
        return "member-artist-page"; // Nama file HTML
    }

    // Halaman Member Add Artist
    @GetMapping("/member-add-artist")
    public String showMemberAddArtistPage() {
        return "member-add-artist"; // Nama file HTML
    }

    // Halaman Member Add Setlist
    @GetMapping("/member-add-setlist")
    public String showMemberAddSetlistPage() {
        return "member-add-setlist"; // Nama file HTML
    }

    // Halaman Member Add Show
    @GetMapping("/member-add-show")
    public String showMemberAddShowPage() {
        return "member-add-show"; // Nama file HTML
    }

    // Halaman Member Comment
    @GetMapping("/member-comment")
    public String showMemberCommentPage() {
        return "member-comment"; // Nama file HTML
    }

    // Halaman Member Show Page
    @GetMapping("/member-show-page")
    public String showMemberShowPage() {
        return "member-show-page"; // Nama file HTML
    }
}

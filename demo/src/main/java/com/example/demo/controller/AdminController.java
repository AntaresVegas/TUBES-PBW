package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/members")
public class AdminController {

    @Autowired
    private MemberService memberService;

    // Get all members
    @GetMapping
    public String getAllMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        model.addAttribute("newMember", new Member());
        return "admin-members"; // Mengarah ke file admin-members.html
    }

    // Add a new member (optional untuk admin)
    @PostMapping("/add")
    public String addMember(@ModelAttribute("newMember") Member member) {
        memberService.addMember(member);
        return "redirect:/admin/members";
    }

    // Update a member
    @PostMapping("/update/{id}")
    public String updateMember(@PathVariable Long id, @ModelAttribute("updatedMember") Member member) {
        memberService.updateMember(id, member);
        return "redirect:/admin/members";
    }

    // Delete a member
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/members";
    }

    
}

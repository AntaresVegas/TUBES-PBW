package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Add a new member
    public Member addMember(Member member) {
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        if (memberRepository.findByUsername(member.getUsername()).isPresent()) {
            throw new RuntimeException("Username already registered");
        }
        return memberRepository.save(member);
    }
    

    // Update an existing member
    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        existingMember.setUsername(updatedMember.getUsername());
        existingMember.setEmail(updatedMember.getEmail());
        existingMember.setPassword(updatedMember.getPassword());
        return memberRepository.save(existingMember);
    }

    // Delete a member by ID
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        memberRepository.deleteById(id);
    }

    public Member registerMember(String fullName, String username, String email, String password) {
        if (memberRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
    
        Member member = new Member();
        member.setFullName(fullName);
        member.setUsername(username);
        member.setEmail(email);
        member.setPassword(password); // Tidak dienkripsi
        Member savedMember = memberRepository.save(member);
        
        System.out.println("Saved Member: " + savedMember); // Debug log
        return savedMember;
    }
    
}

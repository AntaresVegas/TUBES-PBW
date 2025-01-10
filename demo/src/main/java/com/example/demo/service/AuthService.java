package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Member;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AdminRepository adminRepository;

    public String login(String username, String password) {
        Optional<Member> member = memberRepository.findByUsernameAndPassword(username, password);
        if (member.isPresent()) {
            return "Login successful as Member";
        }
    
        Optional<Admin> admin = adminRepository.findByUsernameAndPassword(username, password);
        if (admin.isPresent()) {
            return "Login successful as Admin";
        }
    
        throw new RuntimeException("Invalid username or password");
    }
    
}

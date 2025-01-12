package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Member;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Object login(String email, String password) {
        // Cek apakah email ada di tabel admin
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin; // Jika admin, kembalikan objek admin
        }

        // Cek apakah email ada di tabel member
        Member member = memberRepository.findByEmail(email).orElse(null);
        if (member != null && member.getPassword().equals(password)) {
            return member; // Jika member, kembalikan objek member
        }

        // Jika email/password salah
        throw new RuntimeException("Invalid email or password");
    }
}

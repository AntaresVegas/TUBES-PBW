package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Mendapatkan semua admin
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Menambahkan admin baru
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Memperbarui admin
    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setEmail(adminDetails.getEmail());
        admin.setPassword(adminDetails.getPassword());
        return adminRepository.save(admin);
    }

    // Menghapus admin
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}

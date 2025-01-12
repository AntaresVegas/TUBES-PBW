package com.example.demo.service;

import com.example.demo.entity.Setlist;
import com.example.demo.repository.SetlistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetlistService {

    @Autowired
    private SetlistRepository setlistRepository;

    public List<Setlist> getAllSetlists() {
        return setlistRepository.findAll();
    }

    public Setlist getSetlistById(Long id) {
        return setlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setlist not found!"));
    }
}

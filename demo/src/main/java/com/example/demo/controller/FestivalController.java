package com.example.demo.controller;

import com.example.demo.entity.Festival;
import com.example.demo.entity.User;
import com.example.demo.jdbc.FestivalService;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/festivals")
public class FestivalController {

    @Autowired
    private FestivalService festivalService;

    // Mendapatkan semua festival
    @GetMapping
    public ResponseEntity<List<Festival>> getAllFestivals() {
        return ResponseEntity.ok(festivalService.getAllFestivals());
    }

    // Menambahkan festival baru
    @PostMapping
    public ResponseEntity<Festival> addFestival(@RequestBody Festival festival) {
        return ResponseEntity.ok(festivalService.addFestival(festival));
    }
}

package com.example.demo.controller;

import com.example.demo.entity.Setlist;
import com.example.demo.entity.User;
import com.example.demo.jdbc.SetlistService;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/setlists")
public class SetlistController {

    @Autowired
    private SetlistService setlistService;

    // Mendapatkan semua setlist
    @GetMapping
    public ResponseEntity<List<Setlist>> getAllSetlists() {
        return ResponseEntity.ok(setlistService.getAllSetlists());
    }

    // Menambahkan setlist baru
    @PostMapping
    public ResponseEntity<Setlist> addSetlist(@RequestBody Setlist setlist) {
        return ResponseEntity.ok(setlistService.addSetlist(setlist));
    }
}

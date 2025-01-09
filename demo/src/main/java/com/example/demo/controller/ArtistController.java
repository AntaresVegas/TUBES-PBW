package com.example.demo.controller;

import com.example.demo.entity.Artist;
import com.example.demo.entity.User;
import com.example.demo.jdbc.ArtistService;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    // Mendapatkan semua artis
    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    // Menambahkan artis baru
    @PostMapping
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        return ResponseEntity.ok(artistService.addArtist(artist));
    }
}

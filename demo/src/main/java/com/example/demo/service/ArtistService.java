package com.example.demo.service;

import com.example.demo.dto.ArtistSetlistDTO;
import com.example.demo.entity.Artist;
import com.example.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public List<ArtistSetlistDTO> getArtistsWithSetlistCount() {
        return artistRepository.findArtistsWithSetlistCount();
    }

    // Simpan artist ke database
    public void saveArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public List<Artist> getAllArtists() {
        // Ambil semua artist dari database
        return artistRepository.findAll();
    }

    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Artist with ID " + id + " not found"));
    }

    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }
}

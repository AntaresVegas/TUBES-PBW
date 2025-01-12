package com.example.demo.service;

import com.example.demo.dto.ArtistSetlistDTO;
import com.example.demo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public List<ArtistSetlistDTO> getArtistsWithSetlistCount() {
        return artistRepository.findArtistsWithSetlistCount();
    }
}

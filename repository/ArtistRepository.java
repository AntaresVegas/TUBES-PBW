package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    public void addArtist(String namaArtis, String genreMusik);
}


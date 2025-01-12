package com.example.demo.service;

import com.example.demo.entity.Song;
import com.example.demo.entity.Artist;
import com.example.demo.repository.SongRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Optional<Song> findByTitleAndArtist(String title, Artist artist) {
        return songRepository.findByTitleAndArtist(title, artist);
    }

    public void addSong(Song song) {
        songRepository.save(song);
    }
}

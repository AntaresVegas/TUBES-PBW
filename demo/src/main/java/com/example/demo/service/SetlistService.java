package com.example.demo.service;

import com.example.demo.entity.Artist;
import com.example.demo.entity.Setlist;
import com.example.demo.entity.Song;
import com.example.demo.repository.SetlistRepository;
import com.example.demo.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SetlistService {

    @Autowired
    private SetlistRepository setlistRepository;

    @Autowired
    private SongRepository songRepository;

    public List<Setlist> getAllSetlists() {
        return setlistRepository.findAll();
    }

    public Setlist getSetlistById(Long id) {
        return setlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setlist not found!"));
    }

    public void addSetlist(Setlist setlist) {
        setlistRepository.save(setlist);
    }

    public List<Song> parseSongs(String setlist, Artist artist) {
        String[] songTitles = setlist.split(","); // Pisahkan string berdasarkan koma
        List<Song> songs = new ArrayList<>();
    
        for (String title : songTitles) {
            title = title.trim(); // Hapus spasi di awal/akhir
            Optional<Song> optionalSong = songRepository.findByTitleAndArtist(title, artist);
    
            Song song;
            if (optionalSong.isPresent()) {
                // Jika lagu sudah ada, gunakan lagu yang ada
                song = optionalSong.get();
            } else {
                // Jika lagu tidak ada, buat lagu baru
                song = new Song();
                song.setTitle(title);
                song.setArtist(artist);
                songRepository.save(song); // Simpan ke database
            }
    
            songs.add(song);
        }
        return songs;
    }
    
}

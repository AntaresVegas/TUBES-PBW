package com.example.demo.repository;

import com.example.demo.entity.Artist;
import com.example.demo.entity.Song;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    // Query method untuk mencari lagu berdasarkan judul dan artis
    Optional<Song> findByTitleAndArtist(String title, Artist artist);

    // Alternatif: Gunakan anotasi @Query jika query manual diperlukan
    @Query("SELECT s FROM Song s WHERE s.title = :title AND s.artist = :artist")
    Optional<Song> findSongByTitleAndArtist(@Param("title") String title, @Param("artist") Artist artist);
}

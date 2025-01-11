package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Setlist;
import com.example.demo.entity.Show;

@Repository
public interface SetlistRepository extends JpaRepository<Setlist, Long> {
    void addSetlist(String namaLagu, String showTerkait, int showId); // Tambah Setlist
    List<Setlist> findSetlistsByShowId(int showId); // Ambil Setlist berdasarkan ID Show
    List<Show> findAllShows(); // Ambil semua data Show
}


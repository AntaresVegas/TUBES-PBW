package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {
    void addFestival(String namaFest); // Tambah Show
    List<Festival> findAllFest(); 
}

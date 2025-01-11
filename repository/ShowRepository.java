package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findAllShows();
}

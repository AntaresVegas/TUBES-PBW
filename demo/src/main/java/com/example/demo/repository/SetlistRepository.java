package com.example.demo.repository;

import com.example.demo.entity.Setlist;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetlistRepository extends JpaRepository<Setlist, Long> {
    @EntityGraph(attributePaths = {"show", "show.festival", "show.artist", "songs"})
    Optional<Setlist> findById(Long id);
}

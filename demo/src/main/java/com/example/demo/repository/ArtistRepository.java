package com.example.demo.repository;

import com.example.demo.dto.ArtistSetlistDTO;
import com.example.demo.entity.Artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("SELECT new com.example.demo.dto.ArtistSetlistDTO(a.id, a.name, COUNT(s.id)) " +
       "FROM Artist a " +
       "LEFT JOIN a.setlists s " +
       "GROUP BY a.id, a.name " +
       "ORDER BY COUNT(s.id) DESC")
    List<ArtistSetlistDTO> findArtistsWithSetlistCount();


}

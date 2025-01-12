package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "songs") // Pastikan nama tabel benar
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    private String genre;

    @ManyToMany(mappedBy = "songs")
    private List<Setlist> setlists;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Setlist> getSetlists() {
        return setlists;
    }

    public void setSetlists(List<Setlist> setlists) {
        this.setlists = setlists;
    }
}

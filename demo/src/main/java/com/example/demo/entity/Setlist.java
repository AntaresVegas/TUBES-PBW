package com.example.demo.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "setlists") // Pastikan nama tabel benar
public class Setlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToMany
    @JoinTable(
        name = "setlist_songs", // Nama tabel junction
        joinColumns = @JoinColumn(name = "setlist_id"), // Foreign key ke tabel Setlist
        inverseJoinColumns = @JoinColumn(name = "song_id") // Foreign key ke tabel Song
    )
    private List<Song> songs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}

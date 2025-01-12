package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows") // Pastikan nama tabel sesuai dengan database
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY) // Ubah default FetchType.EAGER menjadi LAZY
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id", nullable = false)
    private Festival festival;

    private Date date;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Setlist> setlists = new ArrayList<>();

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

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Setlist> getSetlists() {
        return setlists;
    }

    public void setSetlists(List<Setlist> setlists) {
        this.setlists = setlists;
    }

    // Utility method to add a setlist
    public void addSetlist(Setlist setlist) {
        setlists.add(setlist);
        setlist.setShow(this);
    }

    // Utility method to remove a setlist
    public void removeSetlist(Setlist setlist) {
        setlists.remove(setlist);
        setlist.setShow(null);
    }
}

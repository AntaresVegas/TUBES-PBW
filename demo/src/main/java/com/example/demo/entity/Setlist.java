package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Setlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Show show;

    @Lob
    private String songs;

    // Getters and Setters
}

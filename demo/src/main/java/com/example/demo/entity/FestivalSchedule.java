package com.example.demo.entity;

import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
public class FestivalSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Festival festival;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Show show;

    @Column(nullable = false)
    private LocalTime time;

    // Getters and Setters
}


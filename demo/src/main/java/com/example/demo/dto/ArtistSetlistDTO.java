package com.example.demo.dto;

public class ArtistSetlistDTO {
    private Long id;
    private String name;
    private Long setlistCount;

    // Constructor
    public ArtistSetlistDTO(Long id, String name, Long setlistCount) {
        this.id = id;
        this.name = name;
        this.setlistCount = setlistCount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSetlistCount() {
        return setlistCount;
    }

    public void setSetlistCount(Long setlistCount) {
        this.setlistCount = setlistCount;
    }
}

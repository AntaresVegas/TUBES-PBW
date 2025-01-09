package com.example.demo.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Festival;
import com.example.demo.repository.FestivalRepository;

@Service
public class FestivalService {

    @Autowired
    private FestivalRepository festivalRepository;

    public List<Festival> getAllFestivals() {
        return festivalRepository.findAll();
    }

    public Festival addFestival(Festival festival) {
        return festivalRepository.save(festival);
    }
}


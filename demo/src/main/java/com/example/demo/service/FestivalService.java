package com.example.demo.service;

import com.example.demo.entity.Festival;
import com.example.demo.repository.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalService {

    @Autowired
    private FestivalRepository festivalRepository;

    public List<Festival> getAllFestivals() {
        return festivalRepository.findAll();
    }
}

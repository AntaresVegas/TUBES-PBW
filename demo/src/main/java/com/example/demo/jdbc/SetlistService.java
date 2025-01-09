package com.example.demo.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Setlist;
import com.example.demo.repository.SetlistRepository;

@Service
public class SetlistService {

    @Autowired
    private SetlistRepository setlistRepository;

    public List<Setlist> getAllSetlists() {
        return setlistRepository.findAll();
    }

    public Setlist addSetlist(Setlist setlist) {
        return setlistRepository.save(setlist);
    }
}


package com.example.demo.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FestivalSchedule;
import com.example.demo.repository.FestivalScheduleRepository;

@Service
public class FestivalScheduleService {

    @Autowired
    private FestivalScheduleRepository festivalScheduleRepository;

    public List<FestivalSchedule> getAllSchedules() {
        return festivalScheduleRepository.findAll();
    }

    public FestivalSchedule addSchedule(FestivalSchedule schedule) {
        return festivalScheduleRepository.save(schedule);
    }
}


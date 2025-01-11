package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FestivalSchedule;

@Repository
public interface FestivalScheduleRepository extends JpaRepository<FestivalSchedule, Long> {
}


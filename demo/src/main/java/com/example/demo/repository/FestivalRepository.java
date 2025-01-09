package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {
}

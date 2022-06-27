package com.ducnt.project2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.project2.dto.Score;

public interface ScoreRepo extends JpaRepository<Score, Integer> {

}

package com.ducnt.project2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.project2.dto.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

}

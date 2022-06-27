package com.ducnt.project2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.project2.dto.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}

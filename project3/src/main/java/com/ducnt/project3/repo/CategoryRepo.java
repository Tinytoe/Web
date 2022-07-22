package com.ducnt.project3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.project3.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer > {
}

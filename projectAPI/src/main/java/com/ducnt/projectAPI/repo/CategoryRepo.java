package com.ducnt.projectAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.projectAPI.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer > {
}

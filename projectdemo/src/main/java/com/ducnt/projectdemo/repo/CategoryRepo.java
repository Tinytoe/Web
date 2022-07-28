package com.ducnt.projectdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.projectdemo.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer > {
}

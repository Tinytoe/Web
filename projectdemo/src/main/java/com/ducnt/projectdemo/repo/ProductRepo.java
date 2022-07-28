package com.ducnt.projectdemo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ducnt.projectdemo.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer > {
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE :x ")
	Page<Product> searchByName(@Param("x") String s, Pageable pageable);
}

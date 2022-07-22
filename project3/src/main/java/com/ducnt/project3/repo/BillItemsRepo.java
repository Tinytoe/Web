package com.ducnt.project3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.project3.entity.BillItems;

public interface BillItemsRepo extends JpaRepository<BillItems, Integer > {
	
	
}

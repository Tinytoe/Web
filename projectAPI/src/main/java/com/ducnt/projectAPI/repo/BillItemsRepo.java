package com.ducnt.projectAPI.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.projectAPI.entity.BillItems;

public interface BillItemsRepo extends JpaRepository<BillItems, Integer > {
	
	
}

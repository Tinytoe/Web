package com.ducnt.projectdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.projectdemo.entity.BillItems;

public interface BillItemsRepo extends JpaRepository<BillItems, Integer > {
	
	
}

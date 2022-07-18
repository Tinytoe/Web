package com.ducnt.projectAPI.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ducnt.projectAPI.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer > {
	
	@Query("SELECT b FROM Bill b WHERE b.buyDate > :date")
	List<Bill> findByBuyDate(@Param("date") Date date);
}

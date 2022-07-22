package com.ducnt.project3.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ducnt.project3.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer> {
	
	@Query("SELECT b FROM Bill b WHERE b.buyDate > :date")
	List<Bill> findByBuyDate(@Param("date") Date date);
	
	@Query("SELECT b.user.id, COUNT(b.user.id) FROM Bill b  WHERE b.user.id = :x  GROUP BY b.user.id")
	List<Object[]> thongKeTheoUser(@Param("x") int x);
	
	@Query("SELECT b FROM Bill b where b.buyDate > :from AND b.buyDate <= :to")
	List<Bill> thongKeTheoNgay(@Param("from") Date dayfrom, @Param("to") Date dayto);
	
	@Query("SELECT MONTH(b.buyDate) as THANG, COUNT(b.id) as SO_LUONG FROM Bill b GROUP BY MONTH(b.buyDate)")
	List<Object[]> thongKeTheoThang();
}

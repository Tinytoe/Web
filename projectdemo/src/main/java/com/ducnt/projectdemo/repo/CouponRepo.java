package com.ducnt.projectdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ducnt.projectdemo.entity.Coupon;
public interface CouponRepo extends JpaRepository<Coupon, Integer > {
	@Query("SELECT c FROM Coupon c WHERE c.couponCode LIKE :x")
	Coupon searchByCouponCode(@Param("x") String x);
}

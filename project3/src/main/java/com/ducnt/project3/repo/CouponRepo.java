package com.ducnt.project3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ducnt.project3.entity.Coupon;
public interface CouponRepo extends JpaRepository<Coupon, Integer > {
	@Query("SELECT c FROM Coupon c WHERE c.couponCode LIKE :x")
	com.ducnt.project3.entity.Coupon searchByCouponCode(@Param("x") String x);
}

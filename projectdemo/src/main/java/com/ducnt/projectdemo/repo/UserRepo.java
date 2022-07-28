package com.ducnt.projectdemo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ducnt.projectdemo.entity.User;
@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.name LIKE :x ")
	Page<User> searchByName(@Param("x") String s, Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.username LIKE :x ")
	Page<User> searchByUserName(@Param("x") String s, Pageable pageable);
	
	User findByUsername(String s);
	
	User findByEmail(String email);

}

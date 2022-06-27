package com.ducnt.project2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.project2.dto.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}

package com.ducnt.project2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducnt.project2.dto.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

}

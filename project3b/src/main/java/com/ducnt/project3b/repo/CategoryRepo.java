package com.ducnt.project3b.repo;

import com.ducnt.project3b.entity.category.Category;
import com.ducnt.project3b.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.name LIKE :x ")
    Page<Category> searchByName(@Param("x") String name, Pageable pageable);

}

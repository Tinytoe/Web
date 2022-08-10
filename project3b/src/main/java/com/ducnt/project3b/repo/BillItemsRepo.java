package com.ducnt.project3b.repo;

import com.ducnt.project3b.entity.billItems.BillItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BillItemsRepo extends JpaRepository<BillItems, Integer> {


}

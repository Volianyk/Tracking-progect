package com.job.tracking.repository;

import com.job.tracking.repository.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {
Optional<Bill> findById(Long id);
}

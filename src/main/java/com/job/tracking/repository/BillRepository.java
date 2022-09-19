package com.job.tracking.repository;

import com.job.tracking.repository.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query(
            value = "select * from bill where bill.amount = ?1",
            nativeQuery = true)
    List<Bill> findBillByAmountNative(String amount);
}

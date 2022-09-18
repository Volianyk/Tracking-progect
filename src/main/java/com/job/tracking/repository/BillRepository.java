package com.job.tracking.repository;

import com.job.tracking.repository.entity.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {

    //  @Query("SELECT*")
    //List<Bill> getAllBills();
}

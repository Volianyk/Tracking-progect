package com.job.tracking.repository;

import com.job.tracking.repository.entity.PaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentProviderRepository extends JpaRepository<PaymentProvider, Long> {

}

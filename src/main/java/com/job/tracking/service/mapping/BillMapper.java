package com.job.tracking.service.mapping;

import com.job.tracking.controller.dto.BillDto;
import com.job.tracking.repository.BillRepository;
import com.job.tracking.repository.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class BillMapper {
    @Autowired
    private BillRepository billRepository;

    public abstract BillDto mapBillToBillDto(Bill bill);
}

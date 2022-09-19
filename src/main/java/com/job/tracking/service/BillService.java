package com.job.tracking.service;

import com.job.tracking.controller.dto.BillDto;

import java.util.List;

public interface BillService {
    List<BillDto> getAllRecipient();

    List<BillDto> getBillsByAmount(String amount);
}

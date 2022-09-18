package com.job.tracking.service.impl;

import com.job.tracking.controller.dto.BillDto;
import com.job.tracking.repository.BillRepository;
import com.job.tracking.repository.entity.Bill;
import com.job.tracking.service.BillService;
import com.job.tracking.service.mapping.BillMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<BillDto> getAllRecipient() {
        List<BillDto> recipient = new ArrayList<>();
        Iterable<Bill> bills = billRepository.findAll(Sort.by(Sort.Direction.ASC, "recipient"));
        for (Bill bill : bills) {
            recipient.add(billMapper.mapBillToBillDto(bill));
        }
        return recipient;
    }
}

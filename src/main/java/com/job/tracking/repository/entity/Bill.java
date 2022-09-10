package com.job.tracking.repository.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue
    private Long id;
    private String amount;
    private String recipient;
}

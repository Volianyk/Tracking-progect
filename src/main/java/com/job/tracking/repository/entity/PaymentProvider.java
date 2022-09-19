package com.job.tracking.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String paymentSystem;
}

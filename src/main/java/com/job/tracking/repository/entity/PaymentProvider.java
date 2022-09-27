package com.job.tracking.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    @Column(unique = true, nullable = false)
    String paymentSystem;


//    @OneToMany(mappedBy = "paymentProvider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @Column(nullable = true)
//    @JsonIgnore
//    private List<UserEntity> userEntities;

}

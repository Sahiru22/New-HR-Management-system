package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "insurances")

public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String insuranceType;
    private Double insuranceFee;
    private Integer period;
    private Double monthlyDeductedAmount;

    @ManyToOne
    private Employee employee;
}

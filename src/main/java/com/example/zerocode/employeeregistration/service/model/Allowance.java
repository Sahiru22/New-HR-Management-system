package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "allowances")
public class Allowance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String allowanceType;
    private Double allowanceFee;
    private String allowanceDate;

    @ManyToOne
    private Employee employee;
}

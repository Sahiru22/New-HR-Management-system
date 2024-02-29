package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dependent_details")
public class DependentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "dependent_name")
    private String name;
    private String relationship;
    private String contactNumber;

    @ManyToOne
    private Employee employee;
}

package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "emergency_contacts")
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contactName;
    private String contactNumber;
    private String relationship;

    @ManyToOne
    private Employee employee;
}

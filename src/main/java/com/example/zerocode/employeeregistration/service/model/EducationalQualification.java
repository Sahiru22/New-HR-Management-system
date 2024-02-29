package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "educational_qualifications")

public class EducationalQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String degree;
    private String diploma;

    @ManyToOne
    private Employee employee;
}

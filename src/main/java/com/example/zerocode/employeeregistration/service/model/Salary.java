package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "salaries")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double basicSalary;
    private String salarySchedule;
    private String salaryDate;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}

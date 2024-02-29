package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee>employees;

    @OneToMany(mappedBy = "department")
    private List<EmployeeDesignation> employeeDesignations;

}

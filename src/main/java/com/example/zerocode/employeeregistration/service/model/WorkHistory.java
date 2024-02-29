package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "work_histories")

public class WorkHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workPlace;
    private String jobTitle;
    private String project;
    private String startDate;
    private String endDate;

    @ManyToOne
    private Employee employee;

}

package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSalaryRequest {
    private Long id;
    private Double basicSalary;
    private String salarySchedule;
    private String salaryDate;
}

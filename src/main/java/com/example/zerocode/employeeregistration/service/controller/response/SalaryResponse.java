package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalaryResponse {
    private Long id;
    private Double basicSalary;
    private String salarySchedule;
    private String salaryDate;
    private String employeeName;

}

package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDesignationResponse {
    private Long id;
    private String jobPosition;
    private String startDate;
    private String endDate;
    private String DepartmentName;
}

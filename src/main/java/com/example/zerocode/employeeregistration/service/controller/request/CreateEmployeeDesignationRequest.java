package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEmployeeDesignationRequest {
    private Long id;
    private String jobPosition;
    private String startDate;
    private String endDate;
}

package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAllowanceRequest {
    private Long id;
    private String allowanceType;
    private Double allowanceFee;
    private String allowanceDate;

}

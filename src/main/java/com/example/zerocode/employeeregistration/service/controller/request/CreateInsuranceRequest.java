package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateInsuranceRequest {
    private Long id;
    private String insuranceType;
    private Double insuranceFee;
    private Integer period;
    private Double monthlyDeductedAmount;
}

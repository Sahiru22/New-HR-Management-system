package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsuranceResponse {
    private Long id;
    private String insuranceType;
    private Double insuranceFee;
    private Integer period;
    private Double monthlyDeductedAmount;
}

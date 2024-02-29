package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBonusRequest {
    private Long id;
    private String bonusType;
    private Double bonusAmount;
    private String bonusDate;
}

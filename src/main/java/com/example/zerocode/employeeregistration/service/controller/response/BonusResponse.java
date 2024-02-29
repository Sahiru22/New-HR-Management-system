package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BonusResponse {
    private Long id;
    private String bonusType;
    private Double bonusAmount;
    private String bonusDate;
}

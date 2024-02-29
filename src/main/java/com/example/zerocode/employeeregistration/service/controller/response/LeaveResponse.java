package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaveResponse {
    private Long id;
    private String leaveType;
    private Integer leaveBalance;
    private String leaveDate;

}

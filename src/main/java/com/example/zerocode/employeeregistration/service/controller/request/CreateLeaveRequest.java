package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateLeaveRequest {
    private Long id;
    private String leaveType;
    private Integer leaveBalance;
    private String leaveDate;
}

package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmergencyContactResponse {
    private Long id;
    private String contactName;
    private String contactNumber;
    private String relationship;
}

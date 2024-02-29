package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEmergencyContactRequest {
    private Long id;
    private String contactName;
    private String contactNumber;
    private String relationship;
}

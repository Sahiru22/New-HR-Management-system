package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateDependentDetailRequest {
    private Long id;
    private String name;
    private String relationship;
    private String contactNumber;
}

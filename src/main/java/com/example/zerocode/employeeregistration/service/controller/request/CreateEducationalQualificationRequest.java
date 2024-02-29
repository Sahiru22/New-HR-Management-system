package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEducationalQualificationRequest {
    private Long id;
    private String degree;
    private String diploma;

}

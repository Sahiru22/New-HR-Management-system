package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationalQualificationResponse {
    private Long id;
    private String degree;
    private String diploma;
}

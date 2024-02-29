package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DependentDetailResponse {
    private Long id;
    private String name;
    private String relationship;
    private String contactNumber;
}

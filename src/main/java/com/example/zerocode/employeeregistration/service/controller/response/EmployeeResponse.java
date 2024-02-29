package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {

    private Long id;
    private String name;
    private Integer age;
    private String address;
    private String gender;
    private String email;
    private String bloodGroup;
    private String maritalStatus;
    private String dateOfBirth;

}

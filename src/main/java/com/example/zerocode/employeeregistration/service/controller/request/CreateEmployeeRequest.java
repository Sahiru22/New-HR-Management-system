package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CreateEmployeeRequest {

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

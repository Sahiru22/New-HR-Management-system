package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateWorkHistoryRequest {
    private Long id;
    private String workPlace;
    private String jobTitle;
    private String project;
    private String startDate;
    private String endDate;

}

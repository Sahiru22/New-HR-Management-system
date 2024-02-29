package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkHistoryResponse {

    private Long id;
    private String workPlace;
    private String jobTitle;
    private String project;
    private String startDate;
    private String endDate;

}

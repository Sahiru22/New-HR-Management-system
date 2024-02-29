package com.example.zerocode.employeeregistration.service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssuedItemResponse {
    private Long id;
    private String itemName;
    private String issuedDate;
    private String returnDate;
}

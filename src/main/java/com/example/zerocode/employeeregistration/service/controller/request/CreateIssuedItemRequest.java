package com.example.zerocode.employeeregistration.service.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateIssuedItemRequest {
    private Long id;
    private String itemName;
    private String issuedDate;
    private String returnDate;
}

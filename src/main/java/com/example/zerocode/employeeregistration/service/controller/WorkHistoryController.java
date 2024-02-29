package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateWorkHistoryRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateWorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.controller.response.WorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.WorkHistoryNotFoundException;
import com.example.zerocode.employeeregistration.service.service.WorkHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class WorkHistoryController {

    private final WorkHistoryService workHistoryService;

    @PostMapping(value = "/employees/{employee-id}/work-histories",headers = "version=v1")
    public CreateWorkHistoryResponse addWorkHistory(@RequestBody CreateWorkHistoryRequest request,
                                                    @PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return workHistoryService.addWorkHistory(request,employeeId);
    }

    @GetMapping(value = "/employees/{employee-id}/work-histories",headers = "version=v1")
    public List<WorkHistoryResponse> getWorkHistory(@PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return workHistoryService.getWorkHistory(employeeId);
    }

    @PutMapping(value = "/employees/{employee-id}/work-histories/{work-history-id}",headers = "version=v1")
    public CreateWorkHistoryResponse updateWorkHistory(@RequestBody CreateWorkHistoryRequest request,
                                  @PathVariable ("employee-id")Long employeeId,
                                  @PathVariable ("work-history-id")Long workHistoryId) throws EmployeeNotFoundException, WorkHistoryNotFoundException {
        return workHistoryService.updateWorkHistory(request,employeeId,workHistoryId);
    }

    @DeleteMapping("/employees/{employee-id}/work-histories/{work-history-id}")
    public CreateWorkHistoryResponse deleteWorkHistory(@PathVariable ("employee-id")Long employeeId,
                                  @PathVariable ("work-history-id")Long workHistoryId) throws EmployeeNotFoundException, WorkHistoryNotFoundException {
        return workHistoryService.deleteWorkHistory(employeeId,workHistoryId);
    }
}

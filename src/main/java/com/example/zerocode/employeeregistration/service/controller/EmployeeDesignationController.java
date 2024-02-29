package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeDesignationRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeDesignationNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.EmployeeDesignationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeDesignationController {

    private final EmployeeDesignationService employeeDesignationService;

    @PostMapping(value = "/employees/{employee-id}/departments/{department-id}/employee-designations",headers = "version=v1")
    public CreateEmployeeDesignationResponse addEmployeeDesignation(@RequestBody CreateEmployeeDesignationRequest request,
                                                                     @PathVariable ("employee-id")Long employeeId,
                                                                     @PathVariable ("department-id")Long departmentId) throws EmployeeNotFoundException, DepartmentNotFoundException {
        return employeeDesignationService.addEmployeeDesignation(request,employeeId,departmentId);
    }

    @GetMapping(value = "/employees/{employee-id}/employee-designations",headers = "version=v1")
    public List<EmployeeDesignationResponse> getEmployeeDesignation(@PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return employeeDesignationService.getEmployeeDesignation(employeeId);
    }

    @PutMapping(value = "/employees/{employee-id}/employee-designations/{employee-designation-id}",headers = "version=v1")
    public CreateEmployeeDesignationResponse updateEmployeeDesignation(@RequestBody CreateEmployeeDesignationRequest request,
                                          @PathVariable ("employee-id")Long employeeId,
                                          @PathVariable ("employee-designation-id")Long employeeDesignationId) throws EmployeeNotFoundException, EmployeeDesignationNotFoundException {
        return employeeDesignationService.updateEmployeeDesignation(request,employeeId,employeeDesignationId);
    }

    @DeleteMapping(value = "/employees/{employee-id}/employee-designations/{employee-designation-id}",headers = "version=v1")
    public CreateEmployeeDesignationResponse deleteEmployeeDesignation(@PathVariable ("employee-designation-id")Long employeeDesignationId,
                                          @PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException, EmployeeDesignationNotFoundException {
        return employeeDesignationService.deleteEmployeeDesignation(employeeDesignationId,employeeId);
    }

}

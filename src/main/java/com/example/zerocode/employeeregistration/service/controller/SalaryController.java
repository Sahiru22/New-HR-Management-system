package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateSalaryRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateSalaryResponse;
import com.example.zerocode.employeeregistration.service.controller.response.SalaryResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.SalaryNotFoundException;
import com.example.zerocode.employeeregistration.service.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @PostMapping(value = "/employees/{employee-id}/salaries",headers = "version=v1")
    public CreateSalaryResponse add(@RequestBody CreateSalaryRequest request,
                                    @PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return salaryService.addSalary(request,employeeId);
    }

    @GetMapping(value = "/employees/{employee-id}/salaries",headers = "version=v1")
    public SalaryResponse getById(@PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return salaryService.getSalaryById(employeeId);
    }

    @GetMapping(value = "salaries",headers = "version=v1")
    public List<SalaryResponse> get(){
        return  salaryService.getAllSalaries();
    }

    @PutMapping(value = "/employees/{employee-id}/salaries/{salary-id}",headers = "version=v1")
    public CreateSalaryResponse update(@RequestBody CreateSalaryRequest request,
                       @PathVariable ("employee-id")Long employeeId,
                       @PathVariable ("salary-id")Long salaryId) throws EmployeeNotFoundException, SalaryNotFoundException {
        return salaryService.updateSalary(request,employeeId,salaryId);
    }

    @DeleteMapping(value = "/employees/{employee-id}/salaries/{salary-id}",headers = "version=v1")
    public CreateSalaryResponse delete(@PathVariable ("employee-id")Long employeeId,
                       @PathVariable ("salary-id")Long salaryId) throws SalaryNotFoundException {
        return salaryService.deleteSalary(employeeId,salaryId);
    }
}

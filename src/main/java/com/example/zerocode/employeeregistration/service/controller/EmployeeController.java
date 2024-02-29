package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(value = "/departments/{department-id}/employees",headers = "version=v1")
    public CreateEmployeeResponse addNewEmployee(@RequestBody CreateEmployeeRequest request,
                                                 @PathVariable ("department-id")Long departmentId)throws DepartmentNotFoundException {
        return employeeService.add(request,departmentId);
    }

    @GetMapping(value = "/employees",headers = "version=v1")
    public List<EmployeeResponse> getAllEmployees(){
        return employeeService.getAll();
    }

    @GetMapping(value = "/employees/{employee-id}",headers = "version=v1")
    public EmployeeResponse getEmployeeById(@PathVariable("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return employeeService.getById(employeeId);
    }

    @DeleteMapping(value = "/employees/{employee-id}",headers = "version=v1")
    public CreateEmployeeResponse deleteEmployee(@PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
       return employeeService.deleteById(employeeId);
    }

    @PutMapping(value = "/employees/{employee-id}",headers = "version=v1")
    public CreateEmployeeResponse updateById(@PathVariable ("employee-id")Long employeeId, @RequestBody CreateEmployeeRequest request) throws EmployeeNotFoundException {
       return employeeService.updateById(employeeId,request);
    }

    @GetMapping(value = "/departments/{department-id}/employees", headers = "version=v1")
    public List<EmployeeResponse> getAllDepartmentEmployees(@PathVariable("department-id")Long departmentId) throws DepartmentNotFoundException{
        return employeeService.getAllEmployeeByDepartmentId(departmentId);
    }
}

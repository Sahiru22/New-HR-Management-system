package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateDepartmentRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateDepartmentResponse;
import com.example.zerocode.employeeregistration.service.controller.response.DepartmentResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DepartmentController {

     private final DepartmentService departmentService;

    @PostMapping(value = "/departments",headers = "version=v1")
    public CreateDepartmentResponse addDepartment(@RequestBody CreateDepartmentRequest request) {
        return departmentService.add(request);
    }

    @GetMapping(value = "/departments",headers = "version=v1")
    public List<DepartmentResponse> getAllDepartments(){
        return departmentService.getAll();
    }

    @GetMapping(value = "departments/{department-id}",headers = "version=v1")
    public DepartmentResponse getDepartmentById(@PathVariable ("department-id")Long departmentId) throws DepartmentNotFoundException {
        return departmentService.getById(departmentId);
    }

    @DeleteMapping(value = "/departments/{department-id}",headers = "version=v1")
    public CreateDepartmentResponse deleteDepartment(@PathVariable ("department-id")Long departmentId) throws DepartmentNotFoundException{
        return departmentService.deleteById(departmentId);
    }

    @PutMapping(value = "departments/{department-id}",headers = "version=v1")
    public CreateDepartmentResponse updateDepartment(@PathVariable ("department-id")Long departmentId,
                                 @RequestBody CreateDepartmentRequest request) throws DepartmentNotFoundException {
        return departmentService.updateById(departmentId,request);
    }


}




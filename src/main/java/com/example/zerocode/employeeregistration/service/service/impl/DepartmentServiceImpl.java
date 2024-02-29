package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateDepartmentRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateDepartmentResponse;
import com.example.zerocode.employeeregistration.service.controller.response.DepartmentResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Department;
import com.example.zerocode.employeeregistration.service.repository.DepartmentRepository;
import com.example.zerocode.employeeregistration.service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public CreateDepartmentResponse add(CreateDepartmentRequest request) {

        Department department = new Department();
        department.setId(request.getId());
        department.setName(request.getName());

        departmentRepository.save(department);

        CreateDepartmentResponse response = new CreateDepartmentResponse();
        response.setId(department.getId());

        System.out.println("department added successfully :" + department.getId());

        return response;
    }

    @Override
    public List<DepartmentResponse> getAll() {
        System.out.println("Getting all department");

        List<Department> departments =departmentRepository.findAll();

        return departments.stream()
                .map(department -> DepartmentResponse.builder()
                        .id(department.getId())
                        .name(department.getName())
                        .build())
                .toList();
    }

    @Override
    public DepartmentResponse getById(Long departmentId) throws DepartmentNotFoundException{
        System.out.println("getting departments by id: " + departmentId);

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Not found department with id:" + departmentId));

        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
        }
        

    @Override
    public CreateDepartmentResponse deleteById(Long departmentId) throws DepartmentNotFoundException {
        System.out.println("department delete by id : " + departmentId);

        Department department = departmentRepository.findById(departmentId)
                        .orElseThrow(() -> new DepartmentNotFoundException("Not found department with id:" + departmentId));

        departmentRepository.delete(department);

        CreateDepartmentResponse response = new CreateDepartmentResponse();
        response.setId(department.getId());

        return response;
    }

    @Override
    public CreateDepartmentResponse updateById(Long departmentId, CreateDepartmentRequest request) throws DepartmentNotFoundException {
        System.out.println("update by id : " + departmentId);

        Department department = departmentRepository.findById(departmentId)
                        .orElseThrow(() -> new DepartmentNotFoundException("Not found department with id:" + departmentId));

            department.setName(request.getName());

            departmentRepository.save(department);

            CreateDepartmentResponse response = new CreateDepartmentResponse();
            response.setId(department.getId());

            return response;

    }
}

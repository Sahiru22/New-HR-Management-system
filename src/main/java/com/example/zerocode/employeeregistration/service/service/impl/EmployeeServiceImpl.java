package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Department;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.repository.DepartmentRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public CreateEmployeeResponse add(CreateEmployeeRequest request, Long departmentId) throws DepartmentNotFoundException {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Not found department with id: " + departmentId));

        Employee employee = new Employee();

        employee.setId(request.getId());
        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setAddress(request.getAddress());
        employee.setGender(request.getGender());
        employee.setEmail(request.getEmail());
        employee.setBloodGroup(request.getBloodGroup());
        employee.setMaritalStatus(request.getMaritalStatus());
        employee.setDateOfBirth(request.getDateOfBirth());

        employee.setDepartment(department);

        employeeRepository.save(employee);

        CreateEmployeeResponse response = new CreateEmployeeResponse();
        response.setId(employee.getId());

        System.out.println("employee added successfully. employee id: " + employee.getId());

        return response;

    }

    @Override
    public List<EmployeeResponse> getAll() {
        System.out.println("Getting all employees");

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(employee -> EmployeeResponse.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .age(employee.getAge())
                        .address(employee.getAddress())
                        .gender(employee.getGender())
                        .email(employee.getEmail())
                        .bloodGroup(employee.getBloodGroup())
                        .maritalStatus(employee.getMaritalStatus())
                        .dateOfBirth(employee.getDateOfBirth())
                        .build())
                .toList();
    }

    @Override
    public EmployeeResponse getById(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("Get employee details by id : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .age(employee.getAge())
                .address(employee.getAddress())
                .gender(employee.getGender())
                .email(employee.getEmail())
                .bloodGroup(employee.getBloodGroup())
                .maritalStatus(employee.getMaritalStatus())
                .dateOfBirth(employee.getDateOfBirth())
                .build();
    }

    @Override
    public CreateEmployeeResponse deleteById(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("employee delete by id: " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        employeeRepository.deleteById(employee.getId());

        CreateEmployeeResponse response = new CreateEmployeeResponse();
        response.setId(employee.getId());

        return response;
    }

    @Override
    public CreateEmployeeResponse updateById(Long employeeId, CreateEmployeeRequest request) throws EmployeeNotFoundException {
        System.out.println("update by id : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setAddress(request.getAddress());
        employee.setGender(request.getGender());
        employee.setEmail(request.getEmail());
        employee.setBloodGroup(request.getBloodGroup());
        employee.setMaritalStatus(request.getMaritalStatus());
        employee.setDateOfBirth(request.getDateOfBirth());

        employeeRepository.save(employee);

        CreateEmployeeResponse response = new CreateEmployeeResponse();
        response.setId(employee.getId());

        return response;

    }

    @Override
    public List<EmployeeResponse> getAllEmployeeByDepartmentId(Long departmentId) throws DepartmentNotFoundException{
        System.out.println("Getting all employees with department id: " + departmentId);

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Not found department with id: " + departmentId));

        List<Employee> employees = employeeRepository.findByDepartment(department);

        return employees.stream()
                .map(employee -> EmployeeResponse.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .age(employee.getAge())
                        .address(employee.getAddress())
                        .gender(employee.getGender())
                        .email(employee.getEmail())
                        .bloodGroup(employee.getBloodGroup())
                        .maritalStatus(employee.getMaritalStatus())
                        .dateOfBirth(employee.getDateOfBirth())
                        .build())
                .toList();
    }
}



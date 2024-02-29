package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.EmployeeDesignation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDesignationRepository extends JpaRepository<EmployeeDesignation,Long> {


    List<EmployeeDesignation> findByEmployee(Employee employee);
}

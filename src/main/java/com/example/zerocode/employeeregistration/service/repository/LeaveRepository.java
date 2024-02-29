package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.controller.request.CreateLeaveRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateLeaveResponse;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave,Long> {


    List<Leave> findByEmployee(Employee employee);
}

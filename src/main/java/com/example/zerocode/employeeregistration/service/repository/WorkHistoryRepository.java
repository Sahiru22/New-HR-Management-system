package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory,Long> {

    List<WorkHistory> findByEmployee(Employee employee);
}

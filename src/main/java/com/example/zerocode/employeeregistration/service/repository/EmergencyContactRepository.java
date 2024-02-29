package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.EmergencyContact;
import com.example.zerocode.employeeregistration.service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact,Long> {
    List<EmergencyContact> findByEmployee(Employee employee);
}

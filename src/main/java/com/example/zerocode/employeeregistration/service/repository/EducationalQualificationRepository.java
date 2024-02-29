package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.EducationalQualification;
import com.example.zerocode.employeeregistration.service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationalQualificationRepository extends JpaRepository<EducationalQualification,Long> {
    List<EducationalQualification> findByEmployee(Employee employee);
}


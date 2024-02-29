package com.example.zerocode.employeeregistration.service.repository;

import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.IssuedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssuedItemRepository extends JpaRepository<IssuedItem,Long> {

    List<IssuedItem> findByEmployee(Employee employee);
}

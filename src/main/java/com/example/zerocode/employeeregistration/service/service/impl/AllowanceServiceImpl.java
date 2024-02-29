package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.AllowanceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateAllowanceResponse;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Allowance;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.repository.AllowanceRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.AllowanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AllowanceServiceImpl implements AllowanceService {

    private final AllowanceRepository allowanceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CreateAllowanceResponse addAllowance(CreateAllowanceRequest request, Long employeeId) throws EmployeeNotFoundException {
        System.out.println("successfully adding allowance by employee id:" + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            Allowance allowance = new Allowance();

            allowance.setId(request.getId());
            allowance.setAllowanceType(request.getAllowanceType());
            allowance.setAllowanceFee(request.getAllowanceFee());
            allowance.setAllowanceDate(request.getAllowanceDate());

            allowance.setEmployee(employee);

            allowanceRepository.save(allowance);

            CreateAllowanceResponse response = new CreateAllowanceResponse();
            response.setId(allowance.getId());

            return response;

    }

    @Override
    public List<AllowanceResponse> getAllowance(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("getting allowance with employee Id : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        List<Allowance> allowances = allowanceRepository.findByEmployee(employee);

        return allowances.stream()
                .map(allowance -> AllowanceResponse.builder()
                        .id(allowance.getId())
                        .allowanceType(allowance.getAllowanceType())
                        .allowanceFee(allowance.getAllowanceFee())
                        .allowanceDate(allowance.getAllowanceDate())
                        .build())
                .toList();
    }

    @Override
    public CreateAllowanceResponse updateAllowance(CreateAllowanceRequest request, Long employeeId, Long allowanceId) throws EmployeeNotFoundException, AllowanceNotFoundException {
        System.out.println("update allowance with employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        Allowance allowance = allowanceRepository.findById(allowanceId)
                        .orElseThrow(() -> new AllowanceNotFoundException("Not found allowance with id:" + allowanceId));

                allowance.setAllowanceType(request.getAllowanceType());
                allowance.setAllowanceFee(request.getAllowanceFee());
                allowance.setAllowanceDate(request.getAllowanceDate());

                allowanceRepository.save(allowance);

                CreateAllowanceResponse response = new CreateAllowanceResponse();
                response.setId(allowance.getId());

                return response;
    }

    @Override
    public CreateAllowanceResponse deleteAllowance(Long employeeId, Long allowanceId) throws EmployeeNotFoundException, AllowanceNotFoundException {
        System.out.println("delete allowance by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        Allowance allowance = allowanceRepository.findById(allowanceId)
                        .orElseThrow(() -> new AllowanceNotFoundException("Not found allowance with id:" + allowanceId));

        allowanceRepository.delete(allowance);

        CreateAllowanceResponse response = new CreateAllowanceResponse();
        response.setId(allowance.getId());

        return response;
    }
}

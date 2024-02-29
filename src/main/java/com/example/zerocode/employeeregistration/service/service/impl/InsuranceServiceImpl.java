package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateInsuranceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateInsuranceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.InsuranceResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.InsuranceNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.Insurance;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.InsuranceRepository;
import com.example.zerocode.employeeregistration.service.service.InsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CreateInsuranceResponse addInsurance(CreateInsuranceRequest request, Long employeeId) throws EmployeeNotFoundException {
        System.out.println("successfully adding insurance with employee id:" + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            Insurance insurance = new Insurance();

            insurance.setId(request.getId());
            insurance.setInsuranceType(request.getInsuranceType());
            insurance.setInsuranceFee(request.getInsuranceFee());
            insurance.setPeriod(request.getPeriod());
            insurance.setMonthlyDeductedAmount(request.getMonthlyDeductedAmount());

            insurance.setEmployee(employee);

            insuranceRepository.save(insurance);

            CreateInsuranceResponse response = new CreateInsuranceResponse();
            response.setId(insurance.getId());

            return  response;
    }

    @Override
    public List<InsuranceResponse> getInsurance(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("getting insurance by employee id : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        List<Insurance> insurances = insuranceRepository.findByEmployee(employee);

        return insurances.stream()
                .map(insurance -> InsuranceResponse.builder()
                        .id(insurance.getId())
                        .insuranceType(insurance.getInsuranceType())
                        .insuranceFee(insurance.getInsuranceFee())
                        .period(insurance.getPeriod())
                        .monthlyDeductedAmount(insurance.getMonthlyDeductedAmount())
                        .build())
                .toList();

    }

    @Override
    public CreateInsuranceResponse updateInsurance(CreateInsuranceRequest request, Long employeeId, Long insuranceId) throws EmployeeNotFoundException, InsuranceNotFoundException {
        System.out.println("update insurance by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        Insurance insurance = insuranceRepository.findById(insuranceId)
                .orElseThrow(() -> new InsuranceNotFoundException("Not found insurance with id:" + insuranceId));

                insurance.setInsuranceType(request.getInsuranceType());
                insurance.setInsuranceFee(request.getInsuranceFee());
                insurance.setPeriod(request.getPeriod());
                insurance.setMonthlyDeductedAmount(request.getMonthlyDeductedAmount());

                insuranceRepository.save(insurance);

                CreateInsuranceResponse response = new CreateInsuranceResponse();
                response.setId(insurance.getId());

                return response;
    }

    @Override
    public CreateInsuranceResponse deleteInsurance(Long employeeId, Long insuranceId) throws EmployeeNotFoundException, InsuranceNotFoundException {
        System.out.println("delete insurance by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        Insurance insurance = insuranceRepository.findById(insuranceId)
                        .orElseThrow(() -> new InsuranceNotFoundException("Not found insurance with id:" + insuranceId));

        insuranceRepository.delete(insurance);

        CreateInsuranceResponse response = new CreateInsuranceResponse();
        response.setId(insurance.getId());

        return response;
    }
}

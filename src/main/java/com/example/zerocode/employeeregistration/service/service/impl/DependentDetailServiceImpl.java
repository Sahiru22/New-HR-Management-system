package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateDependentDetailRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateDependentDetailResponse;
import com.example.zerocode.employeeregistration.service.controller.response.DependentDetailResponse;
import com.example.zerocode.employeeregistration.service.exception.DependentDetailsNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.DependentDetail;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.repository.DependentDetailRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.DependentDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DependentDetailServiceImpl implements DependentDetailService {

    private final DependentDetailRepository dependentDetailRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CreateDependentDetailResponse addDependentDetails(CreateDependentDetailRequest request, Long employeeId) throws EmployeeNotFoundException {
        System.out.println("successfully adding dependent details by employee id:" + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            DependentDetail dependentDetail = new DependentDetail();

            dependentDetail.setId(request.getId());
            dependentDetail.setName(request.getName());
            dependentDetail.setRelationship(request.getRelationship());
            dependentDetail.setContactNumber(request.getContactNumber());

            dependentDetail.setEmployee(employee);

            dependentDetailRepository.save(dependentDetail);

            CreateDependentDetailResponse response = new CreateDependentDetailResponse();
            response.setId(dependentDetail.getId());

            return response;

    }

    @Override
    public List<DependentDetailResponse> getDependentDetails(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("getting qualification by employee id : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        List<DependentDetail> dependentDetails = dependentDetailRepository.findByEmployee(employee);

        return dependentDetails.stream()
                .map(dependentDetail -> DependentDetailResponse.builder()
                        .id(dependentDetail.getId())
                        .name(dependentDetail.getName())
                        .relationship(dependentDetail.getRelationship())
                        .contactNumber(dependentDetail.getContactNumber())
                        .build())
                .toList();

    }

    @Override
    public CreateDependentDetailResponse updateDependentDetails(CreateDependentDetailRequest request, Long employeeId, Long dependentDetailsId) throws EmployeeNotFoundException, DependentDetailsNotFoundException {
        System.out.println("update by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        DependentDetail dependentDetail = dependentDetailRepository.findById(dependentDetailsId)
                        .orElseThrow(() -> new DependentDetailsNotFoundException("Not found dependent details with id:" + dependentDetailsId));

                dependentDetail.setName(request.getName());
                dependentDetail.setRelationship(request.getRelationship());
                dependentDetail.setContactNumber(request.getContactNumber());

                dependentDetailRepository.save(dependentDetail);

                CreateDependentDetailResponse response = new CreateDependentDetailResponse();
                response.setId(dependentDetail.getId());

                return response;

    }

    @Override
    public CreateDependentDetailResponse deleteDependent(Long employeeId, Long dependentDetailsId) throws EmployeeNotFoundException, DependentDetailsNotFoundException {
        System.out.println("delete by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        DependentDetail dependentDetail = dependentDetailRepository.findById(dependentDetailsId)
                .orElseThrow(() -> new DependentDetailsNotFoundException("Not found dependent details with id:" + dependentDetailsId));

        dependentDetailRepository.delete(dependentDetail);

        CreateDependentDetailResponse response = new CreateDependentDetailResponse();
        response.setId(dependentDetail.getId());

        return response;
    }

}

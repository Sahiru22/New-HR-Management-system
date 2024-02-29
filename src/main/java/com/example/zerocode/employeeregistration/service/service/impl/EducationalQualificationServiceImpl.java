package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEducationalQualificationRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEducationalQualificationResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EducationalQualificationResponse;
import com.example.zerocode.employeeregistration.service.exception.EducationQualificationNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.EducationalQualification;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.repository.EducationalQualificationRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.EducationalQualificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EducationalQualificationServiceImpl implements EducationalQualificationService {

    private final EmployeeRepository employeeRepository;
    private final EducationalQualificationRepository educationalQualificationRepository;

    @Override
    public CreateEducationalQualificationResponse addQualifications(CreateEducationalQualificationRequest request, Long employeeId) throws EmployeeNotFoundException {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            EducationalQualification educationalQualification = new EducationalQualification();

            educationalQualification.setId(request.getId());
            educationalQualification.setDegree(request.getDegree());
            educationalQualification.setDiploma(request.getDiploma());

            educationalQualification.setEmployee(employee);

            educationalQualificationRepository.save(educationalQualification);

            CreateEducationalQualificationResponse response = new CreateEducationalQualificationResponse();
            response.setId(educationalQualification.getId());

            System.out.println("successfully adding educational qualifications with employee id:" + employeeId);

            return response;

    }

    @Override
    public List<EducationalQualificationResponse> getQualificationByEmployeeId(Long employeeId) throws EmployeeNotFoundException {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        List<EducationalQualification> educationalQualifications = educationalQualificationRepository.findByEmployee(employee);

        return educationalQualifications.stream()
                .map(educationalQualification -> EducationalQualificationResponse.builder()
                        .id(educationalQualification.getId())
                        .degree(educationalQualification.getDegree())
                        .diploma(educationalQualification.getDiploma())
                        .build())
                .toList();
    }

    @Override
    public CreateEducationalQualificationResponse updateQualificationById(CreateEducationalQualificationRequest request, Long employeeId, Long educationalQualificationId) throws EmployeeNotFoundException, EducationQualificationNotFoundException {
        System.out.println("update by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        EducationalQualification educationalQualification = educationalQualificationRepository.findById(educationalQualificationId)
                .orElseThrow(() -> new EducationQualificationNotFoundException("Not found qualification with id:" + educationalQualificationId));

                educationalQualification.setDegree(request.getDegree());
                educationalQualification.setDiploma(request.getDiploma());

                educationalQualificationRepository.save(educationalQualification);

                CreateEducationalQualificationResponse response = new CreateEducationalQualificationResponse();
                response.setId(educationalQualification.getId());

                return response;
    }

    @Override
    public CreateEducationalQualificationResponse deleteQualifications(Long educationalQualificationId, Long employeeId) throws EmployeeNotFoundException, EducationQualificationNotFoundException {
        System.out.println("delete qualification with employee id:" + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        EducationalQualification educationalQualification = educationalQualificationRepository.findById(educationalQualificationId)
                .orElseThrow(() -> new EducationQualificationNotFoundException("Not found qualification with id:" + educationalQualificationId));

        educationalQualificationRepository.delete(educationalQualification);

        CreateEducationalQualificationResponse response = new CreateEducationalQualificationResponse();
        response.setId(educationalQualification.getId());

        return response;
    }
}


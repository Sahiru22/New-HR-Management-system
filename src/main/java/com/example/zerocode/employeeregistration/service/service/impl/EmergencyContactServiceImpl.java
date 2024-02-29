package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmergencyContactRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.exception.EmergencyContactNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.EmergencyContact;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.repository.EmergencyContactRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.EmergencyContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class EmergencyContactServiceImpl implements EmergencyContactService {

    private final EmergencyContactRepository emergencyContactRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CreateEmergencyContactResponse addEmergencyContact(CreateEmergencyContactRequest request, Long employeeId) throws EmployeeNotFoundException {
        System.out.println("successfully adding emergency contacts with employee id:" + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            EmergencyContact emergencyContact = new EmergencyContact();

            emergencyContact.setId(request.getId());
            emergencyContact.setContactName(request.getContactName());
            emergencyContact.setContactNumber(request.getContactNumber());
            emergencyContact.setRelationship(request.getRelationship());

            emergencyContact.setEmployee(employee);

            emergencyContactRepository.save(emergencyContact);

            CreateEmergencyContactResponse response = new CreateEmergencyContactResponse();
            response.setId(emergencyContact.getId());

            return response;
    }

    @Override
    public List<EmergencyContactResponse> getEmergencyContact(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("getting emergency contact with employeeId : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        List<EmergencyContact> emergencyContacts = emergencyContactRepository.findByEmployee(employee);

        return emergencyContacts.stream()
                .map(emergencyContact -> EmergencyContactResponse.builder()
                        .id(emergencyContact.getId())
                        .contactName(emergencyContact.getContactName())
                        .contactNumber(emergencyContact.getContactNumber())
                        .relationship(emergencyContact.getRelationship())
                        .build())
                .toList();
    }

    @Override
    public CreateEmergencyContactResponse updateEmergencyContact(CreateEmergencyContactRequest request, Long employeeId, Long emergencyContactId) throws EmployeeNotFoundException, EmergencyContactNotFoundException {
        System.out.println("update emergency contact with employeeId : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            EmergencyContact emergencyContact = emergencyContactRepository.findById(emergencyContactId)
                            .orElseThrow(() -> new EmergencyContactNotFoundException("Not found emergency contacts with id:" + emergencyContactId));

                emergencyContact.setContactName(request.getContactName());
                emergencyContact.setContactNumber(request.getContactNumber());
                emergencyContact.setRelationship(request.getRelationship());

                emergencyContactRepository.save(emergencyContact);

                CreateEmergencyContactResponse response = new CreateEmergencyContactResponse();
                response.setId(emergencyContact.getId());

                return response;
    }

    @Override
    public CreateEmergencyContactResponse deleteEmergencyContact(Long employeeId, Long emergencyContactId) throws EmployeeNotFoundException, EmergencyContactNotFoundException {
        System.out.println("delete emergency contact with employeeId : " + employeeId);

        employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        EmergencyContact emergencyContact = emergencyContactRepository.findById(emergencyContactId)
                        .orElseThrow(() -> new EmergencyContactNotFoundException("Not found emergency contacts with id:" + emergencyContactId));

        emergencyContactRepository.delete(emergencyContact);

        CreateEmergencyContactResponse response = new CreateEmergencyContactResponse();
        response.setId(emergencyContact.getId());

        return response;
    }
}

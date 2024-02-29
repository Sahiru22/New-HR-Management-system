package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmergencyContactRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.exception.EmergencyContactNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.EmergencyContactService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactService emergencyContactService;

    @PostMapping(value = "/employees/{employee-id}/emergency-contacts",headers = "version=v1")
    public CreateEmergencyContactResponse addEmergencyContact(@RequestBody CreateEmergencyContactRequest request,
                                                               @PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return emergencyContactService.addEmergencyContact(request,employeeId);
    }

    @GetMapping(value = "/employees/{employee-id}/emergency-contacts",headers = "version=v1")
    public List<EmergencyContactResponse> getEmergencyContact(@PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return emergencyContactService.getEmergencyContact(employeeId);
    }

    @PutMapping(value = "/employees/{employee-id}/emergency-contacts/{emergency-contact-id}",headers = "version=v1")
    public CreateEmergencyContactResponse updateEmergencyContact(@RequestBody CreateEmergencyContactRequest request,
                                       @PathVariable ("employee-id")Long employeeId,
                                       @PathVariable ("emergency-contact-id")Long emergencyContactId) throws EmployeeNotFoundException, EmergencyContactNotFoundException {
        return emergencyContactService.updateEmergencyContact(request,employeeId,emergencyContactId);
    }

    @DeleteMapping(value = "/employees/{employee-id}/emergency-contacts/{emergency-contact-id}",headers = "version=v1")
    public CreateEmergencyContactResponse deleteEmergencyContact(@PathVariable ("employee-id")Long employeeId,
                                       @PathVariable ("emergency-contact-id")Long emergencyContactId) throws EmployeeNotFoundException, EmergencyContactNotFoundException {
        return emergencyContactService.deleteEmergencyContact(employeeId,emergencyContactId);
    }

}

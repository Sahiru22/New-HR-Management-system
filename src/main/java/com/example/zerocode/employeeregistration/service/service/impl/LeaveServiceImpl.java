package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateLeaveRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateLeaveResponse;
import com.example.zerocode.employeeregistration.service.controller.response.LeaveResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.LeaveNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.Leave;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.LeaveRepository;
import com.example.zerocode.employeeregistration.service.service.LeaveService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CreateLeaveResponse addLeave(CreateLeaveRequest request, Long employeeId) throws EmployeeNotFoundException {
        System.out.println("successfully adding leaves with employee id:" + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            Leave leave = new Leave();

            leave.setId(request.getId());
            leave.setLeaveType(request.getLeaveType());
            leave.setLeaveBalance(request.getLeaveBalance());
            leave.setLeaveDate(request.getLeaveDate());

            leave.setEmployee(employee);

            leaveRepository.save(leave);

            CreateLeaveResponse response = new CreateLeaveResponse();
            response.setId(leave.getId());

            return response;
    }

    @Override
    public List<LeaveResponse> getLeaves(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("getting leaves with employeeId : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        List<Leave> leaves = leaveRepository.findByEmployee(employee);

        return leaves.stream()
                .map(leave -> LeaveResponse.builder()
                        .id(leave.getId())
                        .leaveType(leave.getLeaveType())
                        .leaveBalance(leave.getLeaveBalance())
                        .leaveDate(leave.getLeaveDate())
                        .build())
                .toList();
    }

    @Override
    public CreateLeaveResponse updateLeave(CreateLeaveRequest request, Long employeeId, Long leaveId) throws EmployeeNotFoundException, LeaveNotFoundException {
        System.out.println("update leave by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        Leave leave = leaveRepository.findById(leaveId)
                        .orElseThrow(() -> new LeaveNotFoundException("Not found leave with id:" + leaveId));

                leave.setLeaveType(request.getLeaveType());
                leave.setLeaveBalance(request.getLeaveBalance());
                leave.setLeaveDate(request.getLeaveDate());

                leaveRepository.save(leave);

                CreateLeaveResponse response = new CreateLeaveResponse();
                response.setId(leave.getId());

                return  response;
    }

    @Override
    public CreateLeaveResponse deleteLeave(Long employeeId, Long leaveId) throws EmployeeNotFoundException, LeaveNotFoundException {
        System.out.println("delete leave by employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        Leave leave = leaveRepository.findById(leaveId)
                        .orElseThrow(() -> new LeaveNotFoundException("Not found leave with id:" + leaveId));

        leaveRepository.delete(leave);

        CreateLeaveResponse response = new CreateLeaveResponse();
        response.setId(leave.getId());

        return response;
    }
}

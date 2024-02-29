package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateWorkHistoryRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateWorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.controller.response.WorkHistoryResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.WorkHistoryNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.WorkHistory;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.WorkHistoryRepository;
import com.example.zerocode.employeeregistration.service.service.WorkHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkHistoryServiceImpl implements WorkHistoryService {

    private final WorkHistoryRepository workHistoryRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CreateWorkHistoryResponse addWorkHistory(CreateWorkHistoryRequest request, Long employeeId) throws EmployeeNotFoundException {
        System.out.println("successfully added work history with employee id " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));

            WorkHistory workHistory = new WorkHistory();

            workHistory.setId(request.getId());
            workHistory.setWorkPlace(request.getWorkPlace());
            workHistory.setJobTitle(request.getJobTitle());
            workHistory.setProject(request.getProject());
            workHistory.setStartDate(request.getStartDate());
            workHistory.setEndDate(request.getEndDate());

            workHistory.setEmployee(employee);

            workHistoryRepository.save(workHistory);

            CreateWorkHistoryResponse response = new CreateWorkHistoryResponse();
            response.setId(workHistory.getId());

            return response;
    }

    @Override
    public List<WorkHistoryResponse> getWorkHistory(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("getting work history with employeeId : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));

        List<WorkHistory> workHistories = workHistoryRepository.findByEmployee(employee);

        return workHistories.stream()
                .map(workHistory -> WorkHistoryResponse.builder()
                        .id(workHistory.getId())
                        .workPlace(workHistory.getWorkPlace())
                        .jobTitle(workHistory.getJobTitle())
                        .project(workHistory.getProject())
                        .startDate(workHistory.getStartDate())
                        .endDate(workHistory.getEndDate())
                        .build())
                .toList();
    }


    @Override
    public CreateWorkHistoryResponse updateWorkHistory(CreateWorkHistoryRequest request, Long employeeId, Long workHistoryId) throws EmployeeNotFoundException, WorkHistoryNotFoundException {
        System.out.println("update work history with employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id: " + employeeId));

        WorkHistory workHistory = workHistoryRepository.findById(workHistoryId)
                        .orElseThrow(() -> new WorkHistoryNotFoundException("Not found work history with id: " + workHistoryId));

                workHistory.setWorkPlace(request.getWorkPlace());
                workHistory.setJobTitle(request.getJobTitle());
                workHistory.setProject(request.getProject());
                workHistory.setStartDate(request.getStartDate());
                workHistory.setEndDate(request.getEndDate());

                workHistoryRepository.save(workHistory);

                CreateWorkHistoryResponse response = new CreateWorkHistoryResponse();
                response.setId(workHistory.getId());

                return response;
    }

    @Override
    public CreateWorkHistoryResponse deleteWorkHistory(Long employeeId, Long workHistoryId) throws EmployeeNotFoundException, WorkHistoryNotFoundException {
        System.out.println("delete work history with employee id : " + employeeId);

        employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        WorkHistory workHistory = workHistoryRepository.findById(workHistoryId)
                        .orElseThrow(() -> new WorkHistoryNotFoundException("Not found work history with id:" + workHistoryId));

        workHistoryRepository.delete(workHistory);

        CreateWorkHistoryResponse response = new CreateWorkHistoryResponse();
        response.setId(workHistory.getId());

        return  response;
    }
}

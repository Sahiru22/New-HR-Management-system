package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateIssuedItemRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateIssuedItemResponse;
import com.example.zerocode.employeeregistration.service.controller.response.IssuedItemResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.IssuedItemNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.model.IssuedItem;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.IssuedItemRepository;
import com.example.zerocode.employeeregistration.service.service.IssuedItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IssuedItemServiceImpl implements IssuedItemService {

    private final IssuedItemRepository issuedItemRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public CreateIssuedItemResponse addIssuedItem(CreateIssuedItemRequest request, Long employeeId) throws EmployeeNotFoundException {
        System.out.println("successfully adding issued items with employee id:" + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

            IssuedItem issuedItem = new IssuedItem();

            issuedItem.setId(request.getId());
            issuedItem.setItemName(request.getItemName());
            issuedItem.setIssuedDate(request.getIssuedDate());
            issuedItem.setReturnDate(request.getReturnDate());
            
            issuedItem.setEmployee(employee);
            
            issuedItemRepository.save(issuedItem);

            CreateIssuedItemResponse response = new CreateIssuedItemResponse();
            response.setId(issuedItem.getId());
            
            return response;
    }

    @Override
    public List<IssuedItemResponse> getIssuedItem(Long employeeId) throws EmployeeNotFoundException {
        System.out.println("getting issuedItems with employeeId : " + employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        List<IssuedItem> issuedItems = issuedItemRepository.findByEmployee(employee);

        return issuedItems.stream()
                .map(issuedItem -> IssuedItemResponse.builder()
                        .id(issuedItem.getId())
                        .itemName(issuedItem.getItemName())
                        .issuedDate(issuedItem.getIssuedDate())
                        .returnDate(issuedItem.getReturnDate())
                        .build())
                .toList();
    }

    @Override
    public CreateIssuedItemResponse updateIssuedItem(CreateIssuedItemRequest request, Long employeeId, Long issuedItemId) throws EmployeeNotFoundException, IssuedItemNotFoundException {
        System.out.println("update items with employeeId : " + employeeId);

        employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        IssuedItem issuedItem = issuedItemRepository.findById(issuedItemId)
                        .orElseThrow(() -> new IssuedItemNotFoundException("Not found issued item with id:" + issuedItemId));

                issuedItem.setItemName(request.getItemName());
                issuedItem.setIssuedDate(request.getIssuedDate());
                issuedItem.setReturnDate(request.getReturnDate());

                issuedItemRepository.save(issuedItem);

                CreateIssuedItemResponse response = new CreateIssuedItemResponse();
                response.setId(issuedItem.getId());

                return response;
    }

    @Override
    public CreateIssuedItemResponse deleteIssuedItem(Long employeeId, Long issuedItemId) throws EmployeeNotFoundException, IssuedItemNotFoundException {
        System.out.println("delete items with employeeId : " + employeeId);

        employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

        IssuedItem issuedItem = issuedItemRepository.findById(issuedItemId)
                        .orElseThrow(() -> new IssuedItemNotFoundException("Not found issued item with id:" + issuedItemId));

        issuedItemRepository.delete(issuedItem);

        CreateIssuedItemResponse response = new CreateIssuedItemResponse();
        response.setId(issuedItem.getId());

        return response;
    }
}

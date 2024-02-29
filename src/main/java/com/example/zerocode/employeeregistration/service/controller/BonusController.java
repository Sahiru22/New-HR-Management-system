package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateBonusRequest;
import com.example.zerocode.employeeregistration.service.controller.response.BonusResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateBonusResponse;
import com.example.zerocode.employeeregistration.service.exception.BonusNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.BonusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BonusController {

    private final BonusService bonusService;

    @PostMapping(value = "/employees/{employee-id}/bonuses",headers = "version=v1")
    public CreateBonusResponse add(@RequestBody CreateBonusRequest request,
                                   @PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return bonusService.addBonus(request,employeeId);
    }

    @GetMapping(value = "/employees/{employee-id}/bonuses",headers = "version=v1")
    public List<BonusResponse> get(@PathVariable ("employee-id")Long employeeId) throws EmployeeNotFoundException {
        return bonusService.getBonus(employeeId);
    }

    @PutMapping(value = "/employees/{employee-id}/bonuses/{bonus-id}",headers = "version=v1")
    public CreateBonusResponse update(@RequestBody CreateBonusRequest request,
                       @PathVariable ("employee-id")Long employeeId,
                       @PathVariable ("bonus-id")Long bonusId) throws BonusNotFoundException, EmployeeNotFoundException {
        return bonusService.updateBonus(request,employeeId,bonusId);
    }

    @DeleteMapping(value = "/employees/{employee-id}/bonuses/{bonus-id}",headers = "version=v1")
    public CreateBonusResponse delete(@PathVariable ("employee-id")Long employeeId,
                       @PathVariable ("bonus-id")Long bonusId) throws EmployeeNotFoundException, BonusNotFoundException {
        return bonusService.deleteBonus(employeeId,bonusId);
    }


}

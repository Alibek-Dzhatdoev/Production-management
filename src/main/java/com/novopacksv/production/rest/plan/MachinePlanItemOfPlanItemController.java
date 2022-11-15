package com.novopacksv.production.rest.plan;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.plan.MachinePlanItemRequest;
import com.novopacksv.production.dto.plan.MachinePlanItemResponse;
import com.novopacksv.production.model.planModel.MachinePlan;
import com.novopacksv.production.model.planModel.MachinePlanItem;
import com.novopacksv.production.service.plan.MachinePlanItemService;
import com.novopacksv.production.service.plan.MachinePlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "machine-plans/{machinePlanId}/machine-plan-items", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_CMO', 'ROLE_CTO','ROLE_ECONOMIST', 'ROLE_FULL_ACCESS')")
public class MachinePlanItemOfPlanItemController {

    private final MachinePlanItemService machinePlanItemService;

    private final MachinePlanService machinePlanService;

    private final ModelConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<MachinePlanItemResponse>> getAll(@PathVariable("machinePlanId") Long machinePlanId) {
        MachinePlan machinePlan = machinePlanService.findById(machinePlanId);
        List<MachinePlanItem> machinePlanItems = machinePlan.getMachinePlanItems();
        List<MachinePlanItemResponse> responses = conversionService.convert(machinePlanItems, MachinePlanItemResponse.class);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_CMO', 'ROLE_CTO', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<MachinePlanItemResponse> save(@Valid @RequestBody MachinePlanItemRequest request,
                                                        @PathVariable("machinePlanId") Long machinePlanId) {
        MachinePlanItem machinePlanItem = conversionService.convert(request, MachinePlanItem.class);
        machinePlanItem.setMachinePlan(machinePlanService.findById(machinePlanId));
        machinePlanItem = machinePlanItemService.save(machinePlanItem);
        MachinePlanItemResponse response = conversionService.convert(machinePlanItem, MachinePlanItemResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}

package com.novopacksv.production.rest.plan;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.plan.MachinePlanItemRequest;
import com.novopacksv.production.dto.plan.MachinePlanItemResponse;
import com.novopacksv.production.model.planModel.MachinePlanItem;
import com.novopacksv.production.service.plan.MachinePlanItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "machine-plan-items", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_CMO', 'ROLE_CTO','ROLE_ECONOMIST', 'ROLE_FULL_ACCESS')")
public class MachinePlanItemController {

    private final MachinePlanItemService machinePlanItemService;

    private final ModelConversionService conversionService;

    @GetMapping("/{id}")
    public ResponseEntity<MachinePlanItemResponse> getOne(@PathVariable Long id) {
        MachinePlanItem machinePlan = machinePlanItemService.findById(id);
        MachinePlanItemResponse response = conversionService.convert(machinePlan, MachinePlanItemResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_CMO', 'ROLE_CTO', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<MachinePlanItemResponse> update(@PathVariable Long id,
                                                          @Valid @RequestBody MachinePlanItemRequest request) {
        MachinePlanItem machinePlan = conversionService.convert(request, MachinePlanItem.class);
        machinePlan.setId(id);
        machinePlan = machinePlanItemService.update(machinePlan);
        MachinePlanItemResponse response = conversionService.convert(machinePlan, MachinePlanItemResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_CMO', 'ROLE_CTO', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        machinePlanItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

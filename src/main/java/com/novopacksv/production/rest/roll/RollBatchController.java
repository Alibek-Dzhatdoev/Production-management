package com.novopacksv.production.rest.roll;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.roll.RollBatchResponse;
import com.novopacksv.production.model.rollModel.RollBatch;
import com.novopacksv.production.service.roll.RollBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/roll-batches", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_CMO', 'ROLE_CTO'," +
        " 'ROLE_ACCOUNTANT', 'ROLE_ECONOMIST', 'ROLE_STOREKEEPER', 'ROLE_FULL_ACCESS')")
@RequiredArgsConstructor
public class RollBatchController {

    private final RollBatchService rollBatchService;

    private final ModelConversionService conversionService;

    @GetMapping(params = {"date"})
    public ResponseEntity<List<RollBatchResponse>> getAll(@RequestParam("date") LocalDate manufacturedDate) {
        List<RollBatch> rollBatches = rollBatchService.getAll(manufacturedDate);
        List<RollBatchResponse> response = conversionService.convert(rollBatches, RollBatchResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"roll_type_id", "from", "to"})
    public ResponseEntity<List<RollBatchResponse>> getAll(@RequestParam("roll_type_id") Long rollTypeId,
                                                          @RequestParam("from") LocalDate fromManufacturedDate,
                                                          @RequestParam("to") LocalDate toManufacturedDate) {
        List<RollBatch> rollBatches = rollBatchService.getAll(rollTypeId, fromManufacturedDate, toManufacturedDate);
        List<RollBatchResponse> response = conversionService.convert(rollBatches, RollBatchResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"from", "to"})
    public ResponseEntity<List<RollBatchResponse>> getAll(@RequestParam("from") LocalDate fromManufacturedDate,
                                                          @RequestParam("to") LocalDate toManufacturedDate) {
        List<RollBatch> rollBatches = rollBatchService.getAll(fromManufacturedDate, toManufacturedDate);
        List<RollBatchResponse> response = conversionService.convert(rollBatches, RollBatchResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"roll_type_id", "date"})
    public ResponseEntity<RollBatchResponse> getOne(@RequestParam("roll_type_id") Long rollTypeId,
                                                    @RequestParam("date") LocalDate date) {
        RollBatch rollBatch = rollBatchService.getOne(rollTypeId, date);
        RollBatchResponse response = conversionService.convert(rollBatch, RollBatchResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
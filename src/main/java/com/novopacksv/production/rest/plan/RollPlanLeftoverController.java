package com.novopacksv.production.rest.plan;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.roll.RollLeftOverResponse;
import com.novopacksv.production.model.rollModel.RollLeftOver;
import com.novopacksv.production.service.plan.RollPlanLeftoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
@RequestMapping(value = "roll-plan-leftover", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_CMO', 'ROLE_CTO','ROLE_ECONOMIST', 'ROLE_FULL_ACCESS')")
public class RollPlanLeftoverController {

    private final RollPlanLeftoverService rollPlanLeftoverService;

    @Autowired
    @Lazy
    private ModelConversionService conversionService;

    @GetMapping(params = {"id", "date"})
    public ResponseEntity<RollLeftOverResponse> getOneWithoutPlan(@RequestParam ("id") Long rollTypeId,
                                                                  @RequestParam ("date") LocalDate toDate){
        RollLeftOver rollLeftOver = rollPlanLeftoverService.getOneWithoutPlan(rollTypeId, toDate);
        RollLeftOverResponse response = conversionService.convert(rollLeftOver, RollLeftOverResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"id", "to_date"})
    public ResponseEntity<RollLeftOverResponse> getOneTotal(@RequestParam ("id") Long rollTypeId,
                                                            @RequestParam ("to_date") LocalDate toDate){
        RollLeftOver rollLeftOver = rollPlanLeftoverService.getOneTotal(rollTypeId, toDate);
        RollLeftOverResponse response = conversionService.convert(rollLeftOver, RollLeftOverResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"date"})
    public ResponseEntity<List<RollLeftOverResponse>> getAllWithoutPlan(@RequestParam ("date") LocalDate toDate){
        List<RollLeftOver> rollLeftOvers = rollPlanLeftoverService.getAllWithoutPlan(toDate);
        List<RollLeftOverResponse> responses = conversionService.convert(rollLeftOvers, RollLeftOverResponse.class);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping(params = {"to_date"})
    public ResponseEntity<List<RollLeftOverResponse>> getAllTotal(@RequestParam ("to_date") LocalDate toDate){
        List<RollLeftOver> rollLeftOvers = rollPlanLeftoverService.getAllTotal(toDate);
        List<RollLeftOverResponse> responses = conversionService.convert(rollLeftOvers, RollLeftOverResponse.class);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}

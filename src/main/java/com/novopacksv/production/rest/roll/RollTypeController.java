package com.novopacksv.production.rest.roll;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.roll.RollTypeRequest;
import com.novopacksv.production.dto.roll.RollTypeResponse;
import com.novopacksv.production.model.rollModel.RollType;
import com.novopacksv.production.service.roll.RollTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/roll-types", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_CMO', 'ROLE_CTO'," +
        " 'ROLE_ACCOUNTANT', 'ROLE_ECONOMIST', 'ROLE_STOREKEEPER', 'ROLE_FULL_ACCESS')")
@RequiredArgsConstructor
public class RollTypeController {

    private final RollTypeService rollTypeService;

    private final ModelConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<RollTypeResponse>> getAll() {
        List<RollType> rollTypes = rollTypeService.findAll();
        List<RollTypeResponse> response = conversionService.convert(rollTypes, RollTypeResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"thickness"})
    public ResponseEntity<List<RollTypeResponse>> getAll(@RequestParam("thickness") Double thickness) {
        List<RollType> rollTypes = rollTypeService.findAll(thickness);
        List<RollTypeResponse> responses = conversionService.convert(rollTypes, RollTypeResponse.class);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping(params = {"color"})
    public ResponseEntity<List<RollTypeResponse>> getAll(@RequestParam("color") String colorCode) {
        List<RollType> rollTypes = rollTypeService.findAll(colorCode);
        List<RollTypeResponse> responses = conversionService.convert(rollTypes, RollTypeResponse.class);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RollTypeResponse> getOne(@PathVariable Long id) {
        RollType rollType = rollTypeService.findById(id);
        RollTypeResponse response = conversionService.convert(rollType, RollTypeResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_CTO', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<RollTypeResponse> save(@RequestBody @Valid RollTypeRequest request) {
        RollType rollType = conversionService.convert(request, RollType.class);
        rollType = rollTypeService.save(rollType);
        RollTypeResponse response = conversionService.convert(rollType, RollTypeResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_CTO', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<RollTypeResponse> update(@PathVariable Long id, @RequestBody @Valid RollTypeRequest request) {
        RollType rollType = conversionService.convert(request, RollType.class);
        rollType.setId(id);
        rollType = rollTypeService.update(rollType);
        RollTypeResponse response = conversionService.convert(rollType, RollTypeResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_CTO', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rollTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
package com.novopacksv.production.rest.product;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.product.ProductOperationRequest;
import com.novopacksv.production.dto.product.ProductOperationResponse;
import com.novopacksv.production.model.productModel.ProductOperation;
import com.novopacksv.production.service.product.ProductOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "product-operations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_MANAGER', 'ROLE_CMO', 'ROLE_CTO'," +
        " 'ROLE_ACCOUNTANT', 'ROLE_ECONOMIST', 'ROLE_STOREKEEPER', 'ROLE_FULL_ACCESS')")
public class ProductOperationController {

    private final ProductOperationService productOperationService;

    private final ModelConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<ProductOperationResponse>> getAll() {
        List<ProductOperation> productOperations = productOperationService.findAll();
        List<ProductOperationResponse> response =
                conversionService.convert(productOperations, ProductOperationResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"id", "from", "to"})
    public ResponseEntity<List<ProductOperationResponse>> getAll(@RequestParam("id") Long id,
                                                                 @RequestParam("from") LocalDate fromDate,
                                                                 @RequestParam("to") LocalDate toDate) {
        List<ProductOperation> productOperations =
                productOperationService.findAllOperationBetweenDatesByTypeId(id, fromDate, toDate);
        List<ProductOperationResponse> responses =
                conversionService.convert(productOperations, ProductOperationResponse.class);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOperationResponse> getOne(@PathVariable Long id) {
        ProductOperation productOperation = productOperationService.findById(id);
        ProductOperationResponse response = conversionService.convert(productOperation, ProductOperationResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<ProductOperationResponse> save(@Valid @RequestBody ProductOperationRequest request) {
        ProductOperation productOperation = conversionService.convert(request, ProductOperation.class);
        productOperation = productOperationService.save(productOperation);
        ProductOperationResponse response = conversionService.convert(productOperation, ProductOperationResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<ProductOperationResponse> update(@PathVariable Long id,
                                                           @Valid @RequestBody ProductOperationRequest request) {
        ProductOperation productOperation = conversionService.convert(request, ProductOperation.class);
        productOperation.setId(id);
        productOperation = productOperationService.update(productOperation);
        ProductOperationResponse response = conversionService.convert(productOperation, ProductOperationResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productOperationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
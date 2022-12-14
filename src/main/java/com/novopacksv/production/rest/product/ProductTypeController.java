package com.novopacksv.production.rest.product;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.product.ProductTypeRequest;
import com.novopacksv.production.dto.product.ProductTypeResponse;
import com.novopacksv.production.model.productModel.ProductType;
import com.novopacksv.production.service.product.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product-types", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_MANAGER', 'ROLE_CMO', 'ROLE_CTO'," +
        " 'ROLE_ACCOUNTANT', 'ROLE_ECONOMIST', 'ROLE_STOREKEEPER', 'ROLE_FULL_ACCESS')")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    private final ModelConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<ProductTypeResponse>> getList() {
        List<ProductType> productTypes = productTypeService.findAll();
        List<ProductTypeResponse> response = conversionService.convert(productTypes, ProductTypeResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<List<ProductTypeResponse>> getList(@RequestParam("name") String name) {
        List<ProductType> productTypes = productTypeService.findAll(name);
        List<ProductTypeResponse> responses = conversionService.convert(productTypes, ProductTypeResponse.class);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping(params = {"rollTypeId"})
    public ResponseEntity<List<ProductTypeResponse>> getProductTypesByRollTypeIdInNorms(@RequestParam("rollTypeId") Long rollTypeId) {
        List<ProductType> productTypes = productTypeService.getByRollTypeIdInNorms(rollTypeId);
        List<ProductTypeResponse> responses = conversionService.convert(productTypes, ProductTypeResponse.class);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeResponse> getById(@PathVariable Long id) {
        ProductType productType = productTypeService.findById(id);
        ProductTypeResponse response = conversionService.convert(productType, ProductTypeResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_MANAGER', 'ROLE_CTO', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<ProductTypeResponse> save(@Valid @RequestBody ProductTypeRequest request) {
        ProductType productType = conversionService.convert(request, ProductType.class);
        productType = productTypeService.save(productType);
        ProductTypeResponse response = conversionService.convert(productType, ProductTypeResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_MANAGER', 'ROLE_CTO', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<ProductTypeResponse> update(@PathVariable Long id, @Valid @RequestBody ProductTypeRequest request) {
        ProductType productType = conversionService.convert(request, ProductType.class);
        productType.setId(id);
        productType = productTypeService.update(productType);
        ProductTypeResponse response = conversionService.convert(productType, ProductTypeResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_MANAGER', 'ROLE_CTO', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
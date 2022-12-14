package com.novopacksv.production.rest.product;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.product.ProductCheckRequest;
import com.novopacksv.production.dto.product.ProductCheckResponse;
import com.novopacksv.production.model.productModel.ProductCheck;
import com.novopacksv.production.service.product.ProductCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product-checks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_TECHNOLOGIST', 'ROLE_MANAGER', 'ROLE_CMO', 'ROLE_CTO'," +
        " 'ROLE_ACCOUNTANT', 'ROLE_ECONOMIST', 'ROLE_STOREKEEPER', 'ROLE_FULL_ACCESS')")
public class ProductCheckController {

    private final ProductCheckService productCheckService;

    private final ModelConversionService conversionService;

    @GetMapping
    public ResponseEntity<List<ProductCheckResponse>> getAll() {
        List<ProductCheck> productChecks = productCheckService.findAll();
        List<ProductCheckResponse> response =
                conversionService.convert(productChecks, ProductCheckResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = {"product_type_id"})
    public ResponseEntity<ProductCheckResponse> getOne(@RequestParam("product_type_id") Long productTypeId) {
        ProductCheck productCheck = productCheckService.findOneByProductTypeId(productTypeId);
        ProductCheckResponse response = conversionService.convert(productCheck, ProductCheckResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ACCOUNTANT', 'ROLE_STOREKEEPER', 'ROLE_FULL_ACCESS')")
    public ResponseEntity<ProductCheckResponse> update(@PathVariable Long id,
                                                       @RequestBody @Valid ProductCheckRequest request) {
        ProductCheck productCheck = conversionService.convert(request, ProductCheck.class);
        productCheck.setId(id);
        productCheckService.update(productCheck);
        ProductCheckResponse response = conversionService.convert(productCheck, ProductCheckResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

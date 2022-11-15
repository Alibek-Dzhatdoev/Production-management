package com.novopacksv.production.rest.report;

import com.novopacksv.production.converter.ModelConversionService;
import com.novopacksv.production.dto.report.ProductReportResponse;
import com.novopacksv.production.model.reportModel.ProductReport;
import com.novopacksv.production.service.report.ProductReportService;
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
@RequestMapping(value = "${spring.rest.api-url-prefix}/product-reports", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasAnyRole('ROLE_CMO', 'ROLE_CTO', 'ROLE_ECONOMIST', 'ROLE_CEO', 'ROLE_FULL_ACCESS')")
@RequiredArgsConstructor
public class ProductReportController {

    private final ProductReportService productReportService;

    private final ModelConversionService conversionService;

    @GetMapping(params = {"from", "to"})
    public ResponseEntity<List<ProductReportResponse>> getAll(@RequestParam("from") LocalDate from,
                                                              @RequestParam("to") LocalDate to) {
        List<ProductReport> ProductReports = productReportService.getAll(from, to);
        List<ProductReportResponse> response = conversionService.convert(ProductReports, ProductReportResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/insurance-product")
@RestController
@RequiredArgsConstructor
public class InsuranceProductController {
    private final InsuranceProductService insuranceProductService;

    @PostMapping
    public ResponseEntity<Void> createInsuranceProduct (@Valid @RequestBody InsuranceProductDto insuranceProductDto) {
        insuranceProductService.createInsuranceProduct(insuranceProductDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InsuranceProductDto>> getAllInsuranceProducts () {
        List<InsuranceProductDto> insuranceProducts = insuranceProductService.getAllInsuranceProducts();
        return new ResponseEntity<>(insuranceProducts, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsuranceProduct (@PathVariable UUID id) {
        insuranceProductService.deleteInsuranceProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateInsuranceProduct (
            @PathVariable UUID id,
            InsuranceProductUpdateDto insuranceProductEntity
    ) {
        insuranceProductService.updateInsuranceProduct(id,
                insuranceProductEntity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paginated")
    public ResponseEntity<PageResponse<InsuranceProductDto>> getAllInsuranceProductsPaginated (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String filter
    ) {
        return ResponseEntity.ok(insuranceProductService.getAllInsuranceProductsPaginated(page,
                size, filter));
    }
}

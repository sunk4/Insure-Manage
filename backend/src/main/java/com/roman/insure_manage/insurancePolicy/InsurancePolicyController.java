package com.roman.insure_manage.insurancePolicy;

import com.roman.insure_manage.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insurance-policy")
public class InsurancePolicyController {

    private final InsurancePolicyService insurancePolicyService;

    @PostMapping("/quote")
    public ResponseEntity<InsurancePolicyDto> getQuote (@Valid @RequestBody InsurancePolicyDto insurancePolicyDto) {
        return ResponseEntity.ok(insurancePolicyService.getQuote(insurancePolicyDto));
    }

    @PostMapping("/save")
    public ResponseEntity<byte[]> savePolicyAndGeneratePdf (@Valid @RequestBody InsurancePolicyDto insurancePolicyDto) {

        UUID id = insurancePolicyService.savePolicy(insurancePolicyDto);
        byte[] pdfBytes = insurancePolicyService.generatePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; " +
                "filename=InsurancePolicy_" + id + ".pdf");
        headers.setContentLength(pdfBytes.length);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePolicy (
            @PathVariable UUID id,
            @Valid @RequestBody InsurancePolicyUpdateDto insurancePolicyUpdateDto
    ) {
        insurancePolicyService.updatePolicy(id, insurancePolicyUpdateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsurancePolicyDto> getPolicyById (@PathVariable UUID id) {
        return ResponseEntity.ok(insurancePolicyService.getPolicyById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<PageResponse<InsurancePolicyDto>> getAllInsurancePoliciesPaginated (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(insurancePolicyService.getAllInsurancePoliciesPaginated(page, size));
    }

    @GetMapping
    public ResponseEntity<List<InsurancePolicyDto>> getAllInsurancePolicies () {
        return ResponseEntity.ok(insurancePolicyService.getAllInsurancePolicies());
    }

    @GetMapping("/generate-pdf/{id}")
    public ResponseEntity<byte[]> generatePdf (@PathVariable UUID id) {
        byte[] pdfBytes = insurancePolicyService.generatePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; " +
                "filename=InsurancePolicy_" + id + ".pdf");
        headers.setContentLength(pdfBytes.length);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}

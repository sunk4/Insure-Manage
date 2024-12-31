package com.roman.insure_manage.InsuranceRequest;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/insurance-request")
@RequiredArgsConstructor
public class InsuranceRequestController {
    private final InsuranceRequestService insuranceRequestService;

    @PostMapping
    public ResponseEntity<Void> createInsuranceRequest (@Valid @RequestBody InsuranceRequestDto insuranceRequestDto) {

        insuranceRequestService.createInsuranceRequest(insuranceRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InsuranceRequestDto>> getAllInsuranceRequests () {
        List<InsuranceRequestDto> insuranceRequests = insuranceRequestService.getAllInsuranceRequests();
        return new ResponseEntity<>(insuranceRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceRequestDto> getInsuranceRequestById (@PathVariable UUID id) {
        InsuranceRequestDto insuranceRequest = insuranceRequestService.getInsuranceRequestById(id);
        return new ResponseEntity<>(insuranceRequest, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<PageResponse<InsuranceRequestDto>> getAllInsuranceRequestsPaginated (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) UUID clientId,
            @RequestParam(required = false) UUID productId
    ) {
        return ResponseEntity.ok(insuranceRequestService.getAllInsuranceRequestsPaginated(page,
                size, clientId, productId));
    }

}

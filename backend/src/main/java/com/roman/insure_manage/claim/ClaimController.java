package com.roman.insure_manage.claim;

import com.roman.insure_manage.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/claims")
public class ClaimController {
    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<Void> createClaim (@Valid @RequestBody ClaimDto claimDto) {
        claimService.createClaim(claimDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateClaim (
            @PathVariable UUID id,
            @Valid @RequestBody ClaimUpdateDto claimUpdateDto
    ) {
        claimService.updateClaim(id, claimUpdateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaimDto> getClaimById (@PathVariable UUID id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClaimDto>> getAllClaims () {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/paginated")
    public ResponseEntity<PageResponse<ClaimDto>> getAllClaimsPaginated (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(claimService.getAllClaims(page, size));
    }
}
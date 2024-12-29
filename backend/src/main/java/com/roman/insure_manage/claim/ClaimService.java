package com.roman.insure_manage.claim;

import com.roman.insure_manage.common.PageResponse;

import java.util.List;
import java.util.UUID;

public interface ClaimService {
    void createClaim(ClaimDto claimDto);
    void updateClaim(UUID id, ClaimUpdateDto claimUpdateDto);
    ClaimDto getClaimById(UUID id);
    List<ClaimDto> getAllClaims();
    PageResponse<ClaimDto> getAllClaims(int page, int size);
}

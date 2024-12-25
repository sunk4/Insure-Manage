package com.roman.insure_manage.InsuranceRequest;

import com.roman.insure_manage.common.PageResponse;

import java.util.List;
import java.util.UUID;

public interface InsuranceRequestService {

    void createInsuranceRequest (InsuranceRequestDto insuranceRequestDto);

    List<InsuranceRequestDto> getAllInsuranceRequests ();

    InsuranceRequestDto getInsuranceRequestById (UUID id);

    PageResponse<InsuranceRequestDto> getAllInsuranceRequestsPaginated (int page, int size, UUID clientId, UUID productId);
}

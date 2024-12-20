package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.common.PageResponse;

import java.util.List;
import java.util.UUID;

public interface InsuranceProductService {
    void createInsuranceProduct (InsuranceProductDto insuranceProductDto);

    List<InsuranceProductDto> getAllInsuranceProducts ();

    void deleteInsuranceProduct (UUID id);

    void updateInsuranceProduct (UUID id, InsuranceProductUpdateDto insuranceProductEntity);

    PageResponse<InsuranceProductDto> getAllInsuranceProductsPaginated (int page, int size, String filter);
}

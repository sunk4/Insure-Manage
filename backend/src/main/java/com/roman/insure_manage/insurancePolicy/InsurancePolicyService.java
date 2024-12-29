package com.roman.insure_manage.insurancePolicy;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.common.PageResponse;
import com.roman.insure_manage.insuranceProduct.InsuranceProductEntity;

import java.util.List;
import java.util.UUID;

    public interface InsurancePolicyService {
        InsurancePolicyDto getQuote(InsurancePolicyDto insurancePolicyDto);
        double calculatePremium(InsurancePolicyDto insurancePolicyDto, InsuranceProductEntity insuranceProductEntity, ClientEntity clientEntity);
        UUID savePolicy(InsurancePolicyDto insurancePolicyDto);
        void updatePolicy(UUID id, InsurancePolicyUpdateDto insurancePolicyUpdateDto);
        InsurancePolicyDto getPolicyById(UUID id);
        List<InsurancePolicyDto> getAllInsurancePolicies();
        PageResponse<InsurancePolicyDto> getAllInsurancePoliciesPaginated(int page, int size);
        byte[] generatePdf(UUID id);
    }


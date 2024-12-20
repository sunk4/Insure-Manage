package com.roman.insure_manage.insuranceProduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuranceProductRepository  extends JpaRepository<InsuranceProductEntity, UUID> {
    Page<InsuranceProductEntity> findAllByInsuranceProductNameContainingIgnoreCaseOrInsuranceProductDescriptionContainingIgnoreCaseOrInsuranceProductTypeContainingIgnoreCase (String filter, String filter1, String filter2, PageRequest pageRequest);

}

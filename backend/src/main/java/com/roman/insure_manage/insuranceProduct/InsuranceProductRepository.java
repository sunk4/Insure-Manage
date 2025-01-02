package com.roman.insure_manage.insuranceProduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface InsuranceProductRepository extends JpaRepository<InsuranceProductEntity, UUID> {
    Page<InsuranceProductEntity> findByNameIgnoreCaseContaining (
            String filter,
            PageRequest pageRequest
    );


}

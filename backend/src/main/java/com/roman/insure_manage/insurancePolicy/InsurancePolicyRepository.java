package com.roman.insure_manage.insurancePolicy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicyEntity, UUID> {
}

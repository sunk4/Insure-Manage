package com.roman.insure_manage.InsuranceRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface InsuranceRequestRepository extends JpaRepository<InsuranceRequestEntity, UUID>, JpaSpecificationExecutor<InsuranceRequestEntity> {

}

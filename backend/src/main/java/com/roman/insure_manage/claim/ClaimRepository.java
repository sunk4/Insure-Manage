package com.roman.insure_manage.claim;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClaimRepository extends JpaRepository<ClaimEntity, UUID> {

}

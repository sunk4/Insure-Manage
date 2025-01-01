package com.roman.insure_manage.claim;

import com.roman.insure_manage.common.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimUpdateDto {
    private UUID id;
    private UUID policyId;
    private LocalDate dateOfClaim;
    private Double claimAmount;
    private StatusEnum status;
    private String description;
}

package com.roman.insure_manage.claim;

import com.roman.insure_manage.common.StatusEnum;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaimUpdateDto {
    private UUID id;
    private UUID policyId;
    private LocalDate dateOfClaim;
    private Double claimAmount;
    private StatusEnum status;
    private String description;
}

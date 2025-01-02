package com.roman.insure_manage.claim;

import com.roman.insure_manage.common.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaimDto {
    private UUID id;
    @NotNull(message = "Policy ID is required")
    private UUID policyId;
    @NotNull(message = "Date of claim is required")
    private LocalDate dateOfClaim;
    @NotNull(message = "Claim amount is required")
    @Positive(message = "Claim amount must be greater than to 0")
    private Double claimAmount;
    @NotNull(message = "Status is required")
    private StatusEnum status;
    @NotBlank(message = "Description is required")
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdByWorkerId;
    private UUID lastModifiedByWorkerId;

}

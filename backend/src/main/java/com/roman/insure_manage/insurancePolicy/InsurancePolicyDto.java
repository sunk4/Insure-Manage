package com.roman.insure_manage.insurancePolicy;

import com.roman.insure_manage.common.StatusEnum;
import jakarta.validation.constraints.FutureOrPresent;
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
public class InsurancePolicyDto {
    private UUID id;

    @NotNull(message = "Client ID is required")
    private UUID clientId;
    @NotNull(message = "Product ID is required")
    private UUID productId;
    @NotBlank(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be in the future or present")
    private LocalDate startDate;
    @NotBlank(message = "End date is required")
    @FutureOrPresent(message = "End date must be in the future or present")
    private LocalDate endDate;
    @Positive(message = "Premium amount must be a positive number")
    private double premiumAmount;
    private StatusEnum status;
    private Double propertyValue;
    private int tripDuration;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdByWorkerId;
    private UUID lastModifiedByWorkerId;
}
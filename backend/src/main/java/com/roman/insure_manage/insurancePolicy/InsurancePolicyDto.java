package com.roman.insure_manage.insurancePolicy;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsurancePolicyDto {
    private UUID id;

    @NotNull(message = "Client ID is required")
    private String clientId;
    @NotNull(message = "Product ID is required")
    private String productId;
    @NotBlank(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be in the future or present")
    private String startDate;
    @NotBlank(message = "End date is required")
    @FutureOrPresent(message = "End date must be in the future or present")
    private String endDate;
    @Positive(message = "Premium amount must be a positive number")
    private double premiumAmount;
    @NotBlank(message = "Status is required")
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
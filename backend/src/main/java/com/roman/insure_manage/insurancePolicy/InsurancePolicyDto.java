package com.roman.insure_manage.insurancePolicy;

import com.roman.insure_manage.client.ClientDto;
import com.roman.insure_manage.insuranceProduct.InsuranceProductDto;
import com.roman.insure_manage.worker.WorkerEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InsurancePolicyDto {
    private UUID id;

    @NotNull(message = "Client ID is required")
    private UUID clientId;
    private ClientDto client;
    @NotNull(message = "Product ID is required")
    private UUID productId;
    private InsuranceProductDto product;
    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be in the future or present")
    private LocalDate startDate;
    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date must be in the future or present")
    private LocalDate endDate;
    private double premiumAmount;
    private Double propertyValue;
    private int tripDuration;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private WorkerEntity createdByWorker;
    private UUID createdByWorkerId;
    private UUID lastModifiedByWorkerId;
}
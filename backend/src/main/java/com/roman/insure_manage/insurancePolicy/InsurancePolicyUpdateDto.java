package com.roman.insure_manage.insurancePolicy;

import com.roman.insure_manage.common.StatusEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsurancePolicyUpdateDto {
    private UUID id;
    private UUID clientId;
    private UUID productId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double premiumAmount;
    private StatusEnum status;
    private Double propertyValue;
    private int tripDuration;
}

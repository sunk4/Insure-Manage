package com.roman.insure_manage.insurancePolicy;


import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InsurancePolicyUpdateDto {
    private UUID id;
    private UUID clientId;
    private UUID productId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double premiumAmount;
    private Double propertyValue;
    private int tripDuration;
}

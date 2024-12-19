package com.roman.insure_manage.insurancePolicy;

import com.roman.insure_manage.common.BaseDto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsurancePolicyDto extends BaseDto {
    @NotNull
    private String clientId;
    @NotNull
    private String productId;
    @NotBlank
    @FutureOrPresent
    private String startDate;
    @NotBlank
    @FutureOrPresent
    private String endDate;
    @Positive
    private double premiumAmount;
    @NotBlank
    private String status;
}
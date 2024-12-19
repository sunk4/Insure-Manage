package com.roman.insure_manage.transaction;

import com.roman.insure_manage.common.BaseDto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionDto extends BaseDto {
    @NotNull
    private String policyId;
    @NotNull
    @FutureOrPresent
    private String transactionDate;
    @Positive
    private double amount;
    @NotBlank
    private String transactionType;
}
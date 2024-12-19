package com.roman.insure_manage.transaction;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private UUID id;

    @NotNull(message = "Policy ID is required")
    private String policyId;
    @NotNull(message = "Transaction date is required")
    @FutureOrPresent(message = "Transaction date must be in the future or present")
    private String transactionDate;
    @Positive(message = "Amount must be a positive number")
    private double amount;
    @NotBlank(message = "Transaction type is required")
    private String transactionType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
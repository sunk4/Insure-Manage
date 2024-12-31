package com.roman.insure_manage.transaction;

import com.roman.insure_manage.common.StatusEnum;
import jakarta.validation.constraints.FutureOrPresent;
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
public class TransactionDto {
    private UUID id;

    @NotNull(message = "Policy ID is required")
    private UUID policyId;
    private UUID clientId;
    @NotNull(message = "Transaction date is required")
    @FutureOrPresent(message = "Transaction date must be in the future or present")
    private LocalDate transactionDate;
    @Positive(message = "Amount must be a positive number")
    private double amount;
    @NotNull(message = "Status is required")
    private StatusEnum statusEnum;
    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID createdByWorkerId;
    private UUID lastModifiedByWorkerId;
}
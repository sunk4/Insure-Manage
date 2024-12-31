package com.roman.insure_manage.transaction;

import com.roman.insure_manage.common.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionUpdateDto {

    private UUID id;
    private UUID policyId;
    private UUID clientId;
    private LocalDate transactionDate;
    private double amount;
    private String note;
    private StatusEnum statusEnum;
}

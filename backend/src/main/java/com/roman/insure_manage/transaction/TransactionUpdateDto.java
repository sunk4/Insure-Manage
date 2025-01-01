package com.roman.insure_manage.transaction;

import com.roman.insure_manage.common.StatusEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionUpdateDto {

    private UUID id;
    private UUID policyId;
    private UUID clientId;
    private LocalDate transactionDate;
    private Double amount;
    private String note;
    private StatusEnum statusEnum;
}

package com.roman.insure_manage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionDto extends BaseDto{
    private String policyId;
    private String transactionDate;
    private double amount;
    private String transactionType;
}
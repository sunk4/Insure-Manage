package com.roman.insure_manage.transaction;


import com.roman.insure_manage.common.BaseEntity;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private InsurancePolicyEntity policy;
    private Date transactionDate;
    private double amount;
    private String transactionType;
}

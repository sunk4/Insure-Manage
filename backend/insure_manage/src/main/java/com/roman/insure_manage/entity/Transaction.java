package com.roman.insure_manage.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private InsurancePolicy policy;

    private Date transactionDate;
    private double amount;
    private String transactionType;
}

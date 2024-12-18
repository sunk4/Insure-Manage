package com.roman.insure_manage.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID policyId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private InsuranceProduct product;

    private String startDate;
    private String endDate;
    private double premiumAmount;
    private String status;
}

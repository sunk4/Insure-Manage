package com.roman.insure_manage.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity

public class InsuranceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    private String name;
    private String description;

    @OneToMany(mappedBy = "product")
    private List<InsurancePolicy> policies;
}

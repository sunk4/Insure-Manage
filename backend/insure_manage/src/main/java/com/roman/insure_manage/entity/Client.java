package com.roman.insure_manage.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID clientId;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;

    @OneToMany(mappedBy = "client")
    private List<InsurancePolicy> policies;
}

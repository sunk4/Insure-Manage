package com.roman.insure_manage.client;

import com.roman.insure_manage.common.BaseEntity;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InsurancePolicyEntity> policies;
}

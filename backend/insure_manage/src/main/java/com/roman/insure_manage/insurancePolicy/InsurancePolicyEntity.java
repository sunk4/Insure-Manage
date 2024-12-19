package com.roman.insure_manage.insurancePolicy;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.common.BaseEntity;
import com.roman.insure_manage.insuranceProduct.InsuranceProductEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class InsurancePolicyEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")

    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private InsuranceProductEntity product;

    private String startDate;

    private String endDate;

    private double premiumAmount;

    private String status;
}

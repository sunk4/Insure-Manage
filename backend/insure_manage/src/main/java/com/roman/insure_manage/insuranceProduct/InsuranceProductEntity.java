package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.common.BaseEntity;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceProductEntity extends BaseEntity {

    private String name;
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InsurancePolicyEntity> policies;
}

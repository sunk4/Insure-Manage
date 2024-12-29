package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.common.CoverageType;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import com.roman.insure_manage.worker.WorkerEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "insurance_products")
public class InsuranceProductEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private double basePrice;

    @Enumerated(EnumType.STRING)
    private CoverageType coverageType;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InsurancePolicyEntity> policies;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by_worker_id")
    private WorkerEntity createdBy;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by_worker_id")
    private WorkerEntity lastModifiedBy;

}

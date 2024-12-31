package com.roman.insure_manage.claim;

import com.roman.insure_manage.common.StatusEnum;
import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import com.roman.insure_manage.worker.WorkerEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "claims")
public class ClaimEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    @NotNull(message = "Policy ID is required")
    private InsurancePolicyEntity policy;
    @NotNull(message = "Date of claim is required")
    private LocalDate dateOfClaim;
    @NotNull(message = "Claim amount is required")
    @Min(value = 0, message = "Claim amount must be greater than or equal to 0")
    private double claimAmount;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private StatusEnum status;
    @NotBlank(message = "Description is required")
    private String description;
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

package com.roman.insure_manage.transaction;


import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.common.StatusEnum;
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
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private InsurancePolicyEntity policy;
    private LocalDate transactionDate;
    private double amount;
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;
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

package com.roman.insure_manage.InsuranceRequest;

import com.roman.insure_manage.client.ClientEntity;
import com.roman.insure_manage.insuranceProduct.InsuranceProductEntity;
import com.roman.insure_manage.worker.WorkerEntity;
import jakarta.persistence.*;
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
@Table(name = "insurance_requests")
@ToString
public class InsuranceRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NotNull (message = "Client ID is required")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull (message = "Product ID is required")
    private InsuranceProductEntity product;

    @NotNull(message = "Request date is required")
    private LocalDate requestDate;

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

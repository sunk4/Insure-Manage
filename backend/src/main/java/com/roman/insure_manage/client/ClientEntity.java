package com.roman.insure_manage.client;

import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import com.roman.insure_manage.transaction.TransactionEntity;
import com.roman.insure_manage.util.EncryptionUtil;
import com.roman.insure_manage.worker.WorkerEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @Transient
    private String lastName;

    private String encryptedDateOfBirth;
    private String encryptedLastName;
    private String encryptedPhoneNumber;
    private String encryptedAddress;
    private String encryptedZipCode;

    @Transient
    private LocalDate dateOfBirth;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Transient
    private String address;

    private String city;

    @Transient
    private String zipCode;

    private String country;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InsurancePolicyEntity> policies;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionEntity> transactions;

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

    @PrePersist
    @PreUpdate
    private void encryptFields() {
        try {
            if (dateOfBirth != null) {
                this.encryptedDateOfBirth = EncryptionUtil.encrypt(dateOfBirth.toString());
            }
            if (lastName != null) {
                this.encryptedLastName = EncryptionUtil.encrypt(lastName);
            }
            if (phoneNumber != null) {
                this.encryptedPhoneNumber = EncryptionUtil.encrypt(phoneNumber);
            }
            if (address != null) {
                this.encryptedAddress = EncryptionUtil.encrypt(address);
            }
            if (zipCode != null) {
                this.encryptedZipCode = EncryptionUtil.encrypt(zipCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting fields", e);
        }
    }

    @PostLoad
    private void decryptFields() {
        try {
            if (encryptedLastName != null) {
                this.lastName = EncryptionUtil.decrypt(encryptedLastName);
            }
            if (encryptedPhoneNumber != null) {
                this.phoneNumber = EncryptionUtil.decrypt(encryptedPhoneNumber);
            }
            if (encryptedAddress != null) {
                this.address = EncryptionUtil.decrypt(encryptedAddress);
            }
            if (encryptedDateOfBirth != null) {
                this.dateOfBirth = LocalDate.parse(EncryptionUtil.decrypt(encryptedDateOfBirth));
            }
            if (encryptedZipCode != null) {
                this.zipCode = EncryptionUtil.decrypt(encryptedZipCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting fields", e);
        }
    }
}
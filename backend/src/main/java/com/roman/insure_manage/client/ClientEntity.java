package com.roman.insure_manage.client;

import com.roman.insure_manage.insurancePolicy.InsurancePolicyEntity;
import com.roman.insure_manage.util.EncryptionUtil;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Table(name = "clients")
@ToString
public class ClientEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    @Transient
    private String lastName;

    private String encryptedDateOfBirth;
    private String encryptedLastName;
    private String encryptedPhoneNumber;
    private String encryptedAddress;
    private String encryptedZipCode;


    @Transient
    private LocalDateTime dateOfBirth;

    @Column(unique = true)
    private String email;
    private String phoneNumber;
    @Transient
    private String address;
    private String city;
    @Transient
    private String zipCode;
    private String country;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InsurancePolicyEntity> policies;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

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
                this.dateOfBirth = LocalDateTime.parse(EncryptionUtil.decrypt(encryptedDateOfBirth));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting fields", e);
        }
    }
}

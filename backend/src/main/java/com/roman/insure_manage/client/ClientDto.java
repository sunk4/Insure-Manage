package com.roman.insure_manage.client;

import com.roman.insure_manage.insurancePolicy.InsurancePolicyDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private UUID id;

    @NotBlank
    @Size(max = 50, min = 2, message = "First name must be between 2 and 50 characters")
    private String firstName;
    @NotBlank
    @Size(max = 50, min = 2, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")

    private LocalDate dateOfBirth;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must be less than 255 characters")
    private String address;


    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;

    @NotBlank(message = "Zip code is required")
    @Size(max = 10, message = "Zip code must be less than 10 characters")
    private String zipCode;

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country must be less than 50 characters")
    private String country;

    private List<InsurancePolicyDto> policies;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdByWorkerId;
    private UUID lastModifiedByWorkerId;
}
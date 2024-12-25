package com.roman.insure_manage.insuranceProduct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceProductDto {
    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;
    @NotBlank(message = "Description is required")
    @Size(max = 500)
    private String description;
    @NotBlank(message = "Base price is required")
    @Positive(message = "Base price must be greater than 0")
    private BigDecimal basePrice;

    @NotBlank(message = "Coverage type is required")
    private CoverageType coverageType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdByWorkerId;
    private UUID lastModifiedByWorkerId;
}
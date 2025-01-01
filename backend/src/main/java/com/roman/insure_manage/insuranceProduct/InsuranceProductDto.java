package com.roman.insure_manage.insuranceProduct;

import com.roman.insure_manage.common.CoverageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InsuranceProductDto {
    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;
    @NotBlank(message = "Description is required")
    @Size(max = 500)
    private String description;
    @NotNull(message = "Base price is required")
    @Positive(message = "Base price must be greater than 0")
    private Double basePrice;

    @NotNull(message = "Coverage type is required")
    private CoverageType coverageType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdByWorkerId;
    private UUID lastModifiedByWorkerId;
}
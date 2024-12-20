package com.roman.insure_manage.insuranceProduct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InsuranceProductUpdateDto {
    private UUID id;
    @Size(max = 100)
    private String name;
    @Size(max = 500)
    @Positive(message = "Base price must be greater than 0")
    private BigDecimal basePrice;
    private CoverageType coverageType;

}

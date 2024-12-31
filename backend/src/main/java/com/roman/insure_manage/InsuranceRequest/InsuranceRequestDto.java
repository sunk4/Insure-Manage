package com.roman.insure_manage.InsuranceRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InsuranceRequestDto {
    private UUID id;
    @NotNull (message = "Client ID is required")
    private UUID clientId;
    @NotNull (message = "Product ID is required")
    private UUID productId;
    @NotNull(message = "Request date is required")
    private LocalDate requestDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdByWorkerId;
    private UUID lastModifiedByWorkerId;
}

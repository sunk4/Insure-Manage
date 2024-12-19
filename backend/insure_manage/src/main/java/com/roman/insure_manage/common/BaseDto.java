package com.roman.insure_manage.common;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseDto {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
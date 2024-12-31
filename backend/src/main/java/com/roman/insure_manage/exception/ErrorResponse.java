package com.roman.insure_manage.exception;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@Builder
public class ErrorResponse {
    private final String message;
    private final int code;
    private final LocalDateTime timestamp;
    private final List<String> details;

}
package com.hms.user.service.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private boolean success;

    private HttpStatus status;
}

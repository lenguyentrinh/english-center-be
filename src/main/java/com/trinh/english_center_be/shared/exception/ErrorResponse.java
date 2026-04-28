package com.trinh.english_center_be.shared.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorResponse {
    private String code;
    private String message;
    private HttpStatus status;
}

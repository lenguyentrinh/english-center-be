package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================
    // Business Exception
    // =========================
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(
            BusinessException ex,
            HttpServletRequest request
    ) {

        log.error("BusinessException: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .code(ex.getCode())
                .status(ex.getStatus())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown(
            Exception ex,
            HttpServletRequest request
    ) {

        log.error("Unhandled exception: ", ex);

        ErrorCodeEnum error = ErrorCodeEnum.INTERNAL_SERVER_ERROR;

        ErrorResponse response = ErrorResponse.builder()
                .code(error.getCode())
                .status(error.getStatus())
                .message(error.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(error.getStatus()).body(response);
    }
}
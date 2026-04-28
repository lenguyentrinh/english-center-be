package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                        .code(ex.getCode())
                        .status(ex.getStatus())
                        .message(ex.getMessage())
                        .build();
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown(Exception ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode())
                .status(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getStatus())
                .message(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getMessage())
                .build();
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
}

package com.trinh.english_center_be.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {
    private final String code;
    private final HttpStatus status;
    private final String origin;

    public BusinessException(String code,String message, HttpStatus status) {
        this(code, message, status, null);
    }

    public BusinessException(String code, String message, HttpStatus status, String origin) {
        super(message);
        this.code = code;
        this.status = status;
        this.origin = origin;
    }
}

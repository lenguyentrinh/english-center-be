package com.trinh.english_center_be.shared.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodeEnum {
    FORBIDDEN(
            "AUTH_403_01",
            HttpStatus.FORBIDDEN,
            "Access denied"
    ),

    INVALID_CREDENTIAL(
            "AUTH_401_01",
            HttpStatus.UNAUTHORIZED,
            "Email or password incorrect"
    ),

    RESOURCE_NOT_FOUND(
            "COMMON_404_01",
            HttpStatus.NOT_FOUND,
            "Resource not found"
    ),

    TOKEN_EXPIRED(
            "AUTH_401_02",
            HttpStatus.UNAUTHORIZED,
            "Token has expired"
    ),

    UNAUTHORIZED(
            "AUTH_401_03",
            HttpStatus.UNAUTHORIZED,
            "Authentication required"
    ),

    INTERNAL_SERVER_ERROR(
            "INTERNAL_SERVER_ERROR",
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Something went wrong"
    ),

    DATABASE_ERROR(
            "DATABASE_500_01",
            HttpStatus.NOT_FOUND,
            "Database operation failed");

    private final String code;
    private final HttpStatus status;
    private final String message;

    ErrorCodeEnum(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}

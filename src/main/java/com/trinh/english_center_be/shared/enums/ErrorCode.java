package com.trinh.english_center_be.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
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

    VALIDATION_ERROR(
            "COMMON_400_01",
            HttpStatus.BAD_REQUEST,
            "Validation failed"
    ),

    DATABASE_ERROR(
            "DATABASE_500_01",
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Database operation failed"),

    USERNAME_EXISTS(
            "AUTH_400_01",
            HttpStatus.BAD_REQUEST,
            "Username already exists"
    );

    private final String code;
    private final HttpStatus status;
    private final String message;
}

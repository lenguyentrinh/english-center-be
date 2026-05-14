package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeType;
import org.springframework.http.HttpStatus;

public class DatabaseException extends BusinessException {

    public DatabaseException() {
        super(
                ErrorCodeType.DATABASE_ERROR.getCode(),
                ErrorCodeType.DATABASE_ERROR.getMessage(),
                ErrorCodeType.DATABASE_ERROR.getStatus()
        );
    }

    public DatabaseException(String message, HttpStatus status) {
        super(
                ErrorCodeType.DATABASE_ERROR.getCode(),
                message,
                status
        );
    }
}
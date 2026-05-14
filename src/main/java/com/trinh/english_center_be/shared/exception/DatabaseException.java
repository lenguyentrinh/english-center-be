package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class DatabaseException extends BusinessException {

    public DatabaseException() {
        super(
                ErrorCode.DATABASE_ERROR.getCode(),
                ErrorCode.DATABASE_ERROR.getMessage(),
                ErrorCode.DATABASE_ERROR.getStatus()
        );
    }

    public DatabaseException(String message, HttpStatus status) {
        super(
                ErrorCode.DATABASE_ERROR.getCode(),
                message,
                status
        );
    }
}
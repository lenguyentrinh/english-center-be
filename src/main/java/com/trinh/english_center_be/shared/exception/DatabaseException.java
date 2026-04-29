package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class DatabaseException extends BusinessException {

    public DatabaseException() {
        super(
                ErrorCodeEnum.DATABASE_ERROR.getCode(),
                ErrorCodeEnum.DATABASE_ERROR.getMessage(),
                ErrorCodeEnum.DATABASE_ERROR.getStatus()
        );
    }

    public DatabaseException(String message, HttpStatus status) {
        super(
                ErrorCodeEnum.DATABASE_ERROR.getCode(),
                message,
                status
        );
    }
}
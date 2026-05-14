package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeType;

public class InvalidCredentialException extends BusinessException {
    public InvalidCredentialException() {
        super(
                ErrorCodeType.INVALID_CREDENTIAL.getCode(),
                ErrorCodeType.INVALID_CREDENTIAL.getMessage(),
                ErrorCodeType.INVALID_CREDENTIAL.getStatus());
    }

    public InvalidCredentialException(String message) {
        super(ErrorCodeType.INVALID_CREDENTIAL.getCode(), message, ErrorCodeType.INVALID_CREDENTIAL.getStatus());
    }

    public InvalidCredentialException(String message, String origin) {
        super(
                ErrorCodeType.INVALID_CREDENTIAL.getCode(),
                message,
                ErrorCodeType.INVALID_CREDENTIAL.getStatus(),
                origin
        );
    }
}

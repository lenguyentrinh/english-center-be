package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;

public class InvalidCredentialException extends BusinessException {
    public InvalidCredentialException() {
        super(
                ErrorCodeEnum.INVALID_CREDENTIAL.getCode(),
                ErrorCodeEnum.INVALID_CREDENTIAL.getMessage(),
                ErrorCodeEnum.INVALID_CREDENTIAL.getStatus());
    }

    public InvalidCredentialException(String message) {
        super(ErrorCodeEnum.INVALID_CREDENTIAL.getCode(), message, ErrorCodeEnum.INVALID_CREDENTIAL.getStatus());
    }
}

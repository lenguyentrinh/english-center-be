package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class InvalidCredentialException extends BusinessException{
    public InvalidCredentialException(String code, String message, HttpStatus status) {
        super(ErrorCodeEnum.INVALID_CREDENTIAL.getCode(), ErrorCodeEnum.INVALID_CREDENTIAL.getMessage(), ErrorCodeEnum.INVALID_CREDENTIAL.getStatus());
    }
}

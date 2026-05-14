package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeType;

public class UsernameExistsException extends BusinessException {
    public UsernameExistsException() {
        super(
                ErrorCodeType.USERNAME_EXISTS.getCode(),
                ErrorCodeType.USERNAME_EXISTS.getMessage(),
                ErrorCodeType.USERNAME_EXISTS.getStatus()
        );
    }
}

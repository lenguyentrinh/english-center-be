package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;

public class UsernameExistsException extends BusinessException {
    public UsernameExistsException() {
        super(
                ErrorCodeEnum.USERNAME_EXISTS.getCode(),
                ErrorCodeEnum.USERNAME_EXISTS.getMessage(),
                ErrorCodeEnum.USERNAME_EXISTS.getStatus()
        );
    }
}

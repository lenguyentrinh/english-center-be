package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;

public class ForbiddenException extends BusinessException {
    public ForbiddenException() {
        super(
                ErrorCodeEnum.FORBIDDEN.getCode(),
                ErrorCodeEnum.FORBIDDEN.getMessage(),
                ErrorCodeEnum.FORBIDDEN.getStatus());
    }

    public ForbiddenException(String message) {
        super(ErrorCodeEnum.FORBIDDEN.getCode(), message, ErrorCodeEnum.FORBIDDEN.getStatus());
    }
}

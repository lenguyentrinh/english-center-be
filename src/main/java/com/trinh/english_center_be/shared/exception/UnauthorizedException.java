package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() {
        super(
                ErrorCodeEnum.UNAUTHORIZED.getCode(),
                ErrorCodeEnum.UNAUTHORIZED.getMessage(),
                ErrorCodeEnum.UNAUTHORIZED.getStatus());
    }

    public UnauthorizedException(String message) {
        super(ErrorCodeEnum.UNAUTHORIZED.getCode(), message, ErrorCodeEnum.UNAUTHORIZED.getStatus());
    }
}

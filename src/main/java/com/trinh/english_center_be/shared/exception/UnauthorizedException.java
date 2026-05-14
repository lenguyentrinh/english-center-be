package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeType;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() {
        super(
                ErrorCodeType.UNAUTHORIZED.getCode(),
                ErrorCodeType.UNAUTHORIZED.getMessage(),
                ErrorCodeType.UNAUTHORIZED.getStatus());
    }

    public UnauthorizedException(String message) {
        super(ErrorCodeType.UNAUTHORIZED.getCode(), message, ErrorCodeType.UNAUTHORIZED.getStatus());
    }
}

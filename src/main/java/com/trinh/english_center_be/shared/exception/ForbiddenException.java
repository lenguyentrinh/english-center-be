package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeType;

public class ForbiddenException extends BusinessException {
    public ForbiddenException() {
        super(
                ErrorCodeType.FORBIDDEN.getCode(),
                ErrorCodeType.FORBIDDEN.getMessage(),
                ErrorCodeType.FORBIDDEN.getStatus());
    }

    public ForbiddenException(String message) {
        super(ErrorCodeType.FORBIDDEN.getCode(), message, ErrorCodeType.FORBIDDEN.getStatus());
    }
}

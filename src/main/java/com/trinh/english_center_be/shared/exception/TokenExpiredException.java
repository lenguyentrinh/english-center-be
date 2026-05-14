package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeType;

public class TokenExpiredException extends BusinessException {
    public TokenExpiredException() {
        super(ErrorCodeType.TOKEN_EXPIRED.getCode(), ErrorCodeType.TOKEN_EXPIRED.getMessage(), ErrorCodeType.TOKEN_EXPIRED.getStatus());
    }
}

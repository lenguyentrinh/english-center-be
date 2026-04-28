package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;

public class TokenExpiredException extends BusinessException {
    public TokenExpiredException() {
        super(ErrorCodeEnum.TOKEN_EXPIRED.getCode(), ErrorCodeEnum.TOKEN_EXPIRED.getMessage(), ErrorCodeEnum.TOKEN_EXPIRED.getStatus());
    }
}

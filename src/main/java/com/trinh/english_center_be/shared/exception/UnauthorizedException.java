package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;

public class UnauthorizedException extends BusinessException{
    public UnauthorizedException(String code, String message, int status) {
        super(ErrorCodeEnum.UNAUTHORIZED.getCode(), ErrorCodeEnum.UNAUTHORIZED.getMessage(), ErrorCodeEnum.UNAUTHORIZED.getStatus());
    }
}

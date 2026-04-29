package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeEnum;

public class ResourceNotFoundException extends BusinessException{
    public ResourceNotFoundException() {
        super(ErrorCodeEnum.RESOURCE_NOT_FOUND.getCode(), ErrorCodeEnum.RESOURCE_NOT_FOUND.getMessage(), ErrorCodeEnum.RESOURCE_NOT_FOUND.getStatus());
    }

    public ResourceNotFoundException(String code, String message, int status) {
        super(ErrorCodeEnum.RESOURCE_NOT_FOUND.getCode(), ErrorCodeEnum.RESOURCE_NOT_FOUND.getMessage(), ErrorCodeEnum.RESOURCE_NOT_FOUND.getStatus());
    }
}

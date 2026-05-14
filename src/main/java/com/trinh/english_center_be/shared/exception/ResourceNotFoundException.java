package com.trinh.english_center_be.shared.exception;

import com.trinh.english_center_be.shared.enums.ErrorCodeType;

public class ResourceNotFoundException extends BusinessException{
    public ResourceNotFoundException() {
        super(ErrorCodeType.RESOURCE_NOT_FOUND.getCode(), ErrorCodeType.RESOURCE_NOT_FOUND.getMessage(), ErrorCodeType.RESOURCE_NOT_FOUND.getStatus());
    }

    public ResourceNotFoundException( String message) {
        super(ErrorCodeType.RESOURCE_NOT_FOUND.getCode(), message, ErrorCodeType.RESOURCE_NOT_FOUND.getStatus());
    }
}

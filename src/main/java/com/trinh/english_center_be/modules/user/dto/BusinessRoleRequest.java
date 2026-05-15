package com.trinh.english_center_be.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessRoleRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Boolean active;
}

package com.trinh.english_center_be.modules.user.dto;

import com.trinh.english_center_be.shared.enums.BusinessRoles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessRoleRequest {

    @NotBlank(message = "Name must not be blank")
    private BusinessRoles code;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    private Boolean active;
}

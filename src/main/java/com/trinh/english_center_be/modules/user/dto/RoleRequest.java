package com.trinh.english_center_be.modules.user.dto;

import com.trinh.english_center_be.shared.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {

    @NotBlank(message = "Name must not be blank")
    private Roles code;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    private Boolean active;

    private Long businessRoleId;
}

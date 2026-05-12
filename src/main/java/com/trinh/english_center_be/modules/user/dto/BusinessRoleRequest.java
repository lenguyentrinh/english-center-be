package com.trinh.english_center_be.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record BusinessRoleRequest(

        @NotBlank(message = "Name must not be blank")
        @Size(max = 100, message = "Name must be at most 100 characters")
        String name,

        String description,

        Boolean active,

        Set<Long> roleIds
) {
}

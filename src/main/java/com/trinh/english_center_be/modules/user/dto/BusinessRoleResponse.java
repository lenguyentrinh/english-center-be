package com.trinh.english_center_be.modules.user.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record BusinessRoleResponse(
        Long id,
        String name,
        String description,
        Boolean active,
        Set<RoleResponse> roles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

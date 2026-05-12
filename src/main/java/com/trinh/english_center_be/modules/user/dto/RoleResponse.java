package com.trinh.english_center_be.modules.user.dto;

import java.time.LocalDateTime;

public record RoleResponse(
        Long id,
        String name,
        String description,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

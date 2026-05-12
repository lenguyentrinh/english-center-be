package com.trinh.english_center_be.modules.user.dto;

import java.util.Set;

public record UserEffectiveRolesResponse(
        Long userId,
        String username,
        Set<RoleResponse> directRoles,
        Set<BusinessRoleResponse> businessRoles,
        Set<RoleResponse> effectiveRoles
) {
}

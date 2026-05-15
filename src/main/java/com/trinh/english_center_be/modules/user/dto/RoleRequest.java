package com.trinh.english_center_be.modules.user.dto;

import com.trinh.english_center_be.shared.enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    private Roles role;
    private String name;
    private String description;
    private Boolean active;
    private Long businessRoleId;
}

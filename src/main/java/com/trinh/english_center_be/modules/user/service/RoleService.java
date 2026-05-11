package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.entity.Role;
import com.trinh.english_center_be.shared.enums.Roles;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleByRoleName(Roles role);
}

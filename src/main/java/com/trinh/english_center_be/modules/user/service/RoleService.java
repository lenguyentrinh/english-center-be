package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.dto.RoleRequest;
import com.trinh.english_center_be.modules.user.dto.RoleResponse;
import com.trinh.english_center_be.modules.user.entity.Role;
import com.trinh.english_center_be.shared.enums.Roles;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleByRoleName(Roles role);
    List<RoleResponse> findAll();
    RoleResponse findById(Long id);
    RoleResponse create(RoleRequest request);
    RoleResponse update(Long id, RoleRequest request);
    void delete(Long id);
}

package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.Repository.RoleRepository;
import com.trinh.english_center_be.modules.user.dto.RoleRequest;
import com.trinh.english_center_be.modules.user.dto.RoleResponse;
import com.trinh.english_center_be.modules.user.entity.Role;
import com.trinh.english_center_be.shared.enums.Roles;
import com.trinh.english_center_be.shared.exception.ResourceNotFoundException;
import com.trinh.english_center_be.shared.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final BRoleService bRoleService;

    @Override
    public Optional<Role> findRoleByRoleName(Roles role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public RoleResponse findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(StringUtil.NOT_FOUND_BY_ID, StringUtil.ROLE, id))
        );
        return toResponse(role);
    }

    @Override
    public RoleResponse create(RoleRequest roleRequest) {
        Role role = Role.builder()
                .role(roleRequest.getRole())
                .name(roleRequest.getName())
                .description(roleRequest.getDescription())
                .active(roleRequest.getActive() != null ? roleRequest.getActive() : true)
                .build();

        if (roleRequest.getBusinessRoleId() != null) {
            role.setBusinessRole(bRoleService.findById(roleRequest.getBusinessRoleId()));
        }

        return toResponse(roleRepository.save(role));
    }

    @Override
    public void softDeleteById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(StringUtil.NOT_FOUND_BY_ID, StringUtil.ROLE, id))
        );

        role.setActive(false);
        roleRepository.save(role);
    }

    @Override
    public RoleResponse updateById(Long id, RoleRequest roleRequest) {
        Role existing = roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(StringUtil.NOT_FOUND_BY_ID, StringUtil.ROLE, id))
        );

        if (roleRequest.getRole() != null) {
            existing.setRole(roleRequest.getRole());
        }
        if (roleRequest.getName() != null) {
            existing.setName(roleRequest.getName());
        }
        if (roleRequest.getDescription() != null) {
            existing.setDescription(roleRequest.getDescription());
        }
        if (roleRequest.getActive() != null) {
            existing.setActive(roleRequest.getActive());
        }
        if (roleRequest.getBusinessRoleId() != null) {
            existing.setBusinessRole(bRoleService.findById(roleRequest.getBusinessRoleId()));
        }

        return toResponse(roleRepository.save(existing));
    }

    private RoleResponse toResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .role(role.getRole())
                .name(role.getName())
                .description(role.getDescription())
                .active(role.getActive())
                .businessRoleId(role.getBusinessRole() != null ? role.getBusinessRole().getId() : null)
                .businessRoleName(role.getBusinessRole() != null ? role.getBusinessRole().getName() : null)
                .build();
    }
}

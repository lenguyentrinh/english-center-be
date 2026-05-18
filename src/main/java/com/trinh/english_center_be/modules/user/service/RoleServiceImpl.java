package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.Repository.RoleRepository;
import com.trinh.english_center_be.modules.user.dto.RoleRequest;
import com.trinh.english_center_be.modules.user.dto.RoleResponse;
import com.trinh.english_center_be.modules.user.entity.BusinessRole;
import com.trinh.english_center_be.modules.user.entity.Role;
import com.trinh.english_center_be.shared.enums.Roles;
import com.trinh.english_center_be.shared.exception.BusinessException;
import com.trinh.english_center_be.shared.exception.ResourceNotFoundException;
import com.trinh.english_center_be.shared.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final BRoleService bRoleService;

    @Override
    public Optional<Role> findRoleByRoleName(Roles role) {
        return roleRepository.findByRole(role);
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findByActiveTrue().stream().map(this::toResponse).toList();
    }

    @Override
    public RoleResponse findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(StringUtil.NOT_FOUND_BY_ID, StringUtil.ROLE, id))
        );
        if (Boolean.FALSE.equals(role.getActive())) {
            throw new ResourceNotFoundException(String.format(StringUtil.NOT_FOUND_BY_ID, StringUtil.ROLE, id));
        }
        return toResponse(role);
    }

    @Override
    @Transactional
    public RoleResponse create(RoleRequest roleRequest) {
        if (roleRequest.getRole() != null) {
            throw new BusinessException("System roles cannot be created via API", HttpStatus.BAD_REQUEST);
        }
        if (roleRepository.existsByName(roleRequest.getName())) {
            throw new BusinessException(
                    String.format(StringUtil.ENTITY_ALREADY_EXISTS, StringUtil.ROLE),
                    HttpStatus.CONFLICT
            );
        }

        Role role = Role.builder()
                .name(roleRequest.getName())
                .description(roleRequest.getDescription())
                .active(roleRequest.getActive() != null ? roleRequest.getActive() : true)
                .build();

        if (roleRequest.getBusinessRoleId() != null) {
            role.setBusinessRole(resolveActiveBusinessRole(roleRequest.getBusinessRoleId()));
        }

        return toResponse(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void softDeleteById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(StringUtil.NOT_FOUND_BY_ID, StringUtil.ROLE, id))
        );
        if (role.getRole() != null) {
            throw new BusinessException("System roles cannot be deleted", HttpStatus.BAD_REQUEST);
        }
        role.setActive(false);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public RoleResponse updateById(Long id, RoleRequest roleRequest) {
        Role existing = roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(StringUtil.NOT_FOUND_BY_ID, StringUtil.ROLE, id))
        );

        if (existing.getRole() != null) {
            throw new BusinessException("System roles cannot be updated via API", HttpStatus.BAD_REQUEST);
        }

        if (roleRequest.getName() != null && !roleRequest.getName().equals(existing.getName())) {
            if (roleRepository.existsByNameAndIdNot(roleRequest.getName(), id)) {
                throw new BusinessException(
                        String.format(StringUtil.ENTITY_ALREADY_EXISTS, StringUtil.ROLE),
                        HttpStatus.CONFLICT
                );
            }
            existing.setName(roleRequest.getName());
        }
        if (roleRequest.getDescription() != null) {
            existing.setDescription(roleRequest.getDescription());
        }
        if (roleRequest.getActive() != null) {
            existing.setActive(roleRequest.getActive());
        }
        if (roleRequest.getBusinessRoleId() != null) {
            existing.setBusinessRole(resolveActiveBusinessRole(roleRequest.getBusinessRoleId()));
        }

        return toResponse(roleRepository.save(existing));
    }

    private BusinessRole resolveActiveBusinessRole(Long businessRoleId) {
        BusinessRole businessRole = bRoleService.findById(businessRoleId);
        if (Boolean.FALSE.equals(businessRole.getActive())) {
            throw new BusinessException("Business role is inactive", HttpStatus.BAD_REQUEST);
        }
        return businessRole;
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

package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.Repository.RoleRepository;
import com.trinh.english_center_be.modules.user.dto.RoleRequest;
import com.trinh.english_center_be.modules.user.dto.RoleResponse;
import com.trinh.english_center_be.modules.user.entity.Role;
import com.trinh.english_center_be.shared.enums.Roles;
import com.trinh.english_center_be.shared.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findRoleByRoleName(Roles role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public RoleResponse findById(Long id) {
        return toResponse(getById(id));
    }

    @Override
    @Transactional
    public RoleResponse create(RoleRequest request) {
        Role role = Role.builder()
                .name(request.name())
                .description(request.description())
                .active(request.active() != null ? request.active() : true)
                .build();

        return toResponse(roleRepository.save(role));
    }

    @Override
    @Transactional
    public RoleResponse update(Long id, RoleRequest request) {
        Role role = getById(id);
        role.setName(request.name());
        role.setDescription(request.description());
        if (request.active() != null) {
            role.setActive(request.active());
        }

        return toResponse(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Role role = getById(id);
        if (role.getRole() != null) {
            throw new IllegalStateException("Cannot delete system role: " + role.getRole().name());
        }
        roleRepository.delete(role);
    }

    private Role getById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
    }

    private RoleResponse toResponse(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getName(),
                role.getDescription(),
                role.getActive(),
                role.getCreatedAt(),
                role.getUpdatedAt()
        );
    }
}

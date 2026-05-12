package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.Repository.BusinessRoleRepository;
import com.trinh.english_center_be.modules.user.Repository.RoleRepository;
import com.trinh.english_center_be.modules.user.dto.BusinessRoleRequest;
import com.trinh.english_center_be.modules.user.dto.BusinessRoleResponse;
import com.trinh.english_center_be.modules.user.dto.RoleResponse;
import com.trinh.english_center_be.modules.user.entity.BusinessRole;
import com.trinh.english_center_be.modules.user.entity.Role;
import com.trinh.english_center_be.shared.exception.ResourceNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessRoleServiceImpl implements BusinessRoleService {

    private final BusinessRoleRepository businessRoleRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<BusinessRoleResponse> findAll() {
        return businessRoleRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public BusinessRoleResponse findById(Long id) {
        return toResponse(getById(id));
    }

    @Override
    @Transactional
    public BusinessRoleResponse create(BusinessRoleRequest request) {
        BusinessRole businessRole = BusinessRole.builder()
                .name(request.name())
                .description(request.description())
                .active(request.active() != null ? request.active() : true)
                .build();

        if (request.roleIds() != null && !request.roleIds().isEmpty()) {
            Set<Role> roles = new HashSet<>(roleRepository.findAllById(request.roleIds()));
            businessRole.setRoles(roles);
        }

        return toResponse(businessRoleRepository.save(businessRole));
    }

    @Override
    @Transactional
    public BusinessRoleResponse update(Long id, BusinessRoleRequest request) {
        BusinessRole businessRole = getById(id);
        businessRole.setName(request.name());
        businessRole.setDescription(request.description());
        if (request.active() != null) {
            businessRole.setActive(request.active());
        }

        if (request.roleIds() != null) {
            Set<Role> roles = new HashSet<>(roleRepository.findAllById(request.roleIds()));
            businessRole.setRoles(roles);
        }

        return toResponse(businessRoleRepository.save(businessRole));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        BusinessRole businessRole = getById(id);
        businessRoleRepository.delete(businessRole);
    }

    @Override
    @Transactional
    public BusinessRoleResponse addRoles(Long businessRoleId, List<Long> roleIds) {
        BusinessRole businessRole = getById(businessRoleId);
        List<Role> rolesToAdd = roleRepository.findAllById(roleIds);

        if (rolesToAdd.size() != roleIds.size()) {
            throw new ResourceNotFoundException("One or more roles not found");
        }

        businessRole.getRoles().addAll(rolesToAdd);
        return toResponse(businessRoleRepository.save(businessRole));
    }

    @Override
    @Transactional
    public BusinessRoleResponse removeRoles(Long businessRoleId, List<Long> roleIds) {
        BusinessRole businessRole = getById(businessRoleId);
        businessRole.getRoles().removeIf(r -> roleIds.contains(r.getId()));
        return toResponse(businessRoleRepository.save(businessRole));
    }

    private BusinessRole getById(Long id) {
        return businessRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Business role not found with id: " + id));
    }

    private BusinessRoleResponse toResponse(BusinessRole br) {
        Set<RoleResponse> roles = br.getRoles().stream()
                .map(r -> new RoleResponse(
                        r.getId(), r.getName(), r.getDescription(),
                        r.getActive(), r.getCreatedAt(), r.getUpdatedAt()
                ))
                .collect(Collectors.toSet());

        return new BusinessRoleResponse(
                br.getId(), br.getName(), br.getDescription(),
                br.getActive(), roles,
                br.getCreatedAt(), br.getUpdatedAt()
        );
    }
}

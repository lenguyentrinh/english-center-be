package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.Repository.BusinessRoleRepository;
import com.trinh.english_center_be.modules.user.Repository.RoleRepository;
import com.trinh.english_center_be.modules.user.Repository.UserBusinessRoleRepository;
import com.trinh.english_center_be.modules.user.Repository.UserRoleRepository;
import com.trinh.english_center_be.modules.user.dto.BusinessRoleResponse;
import com.trinh.english_center_be.modules.user.dto.RoleResponse;
import com.trinh.english_center_be.modules.user.dto.UserEffectiveRolesResponse;
import com.trinh.english_center_be.modules.user.entity.*;
import com.trinh.english_center_be.shared.exception.BusinessException;
import com.trinh.english_center_be.shared.exception.ResourceNotFoundException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRoleAssignmentServiceImpl implements UserRoleAssignmentService {

    private final UserRoleRepository userRoleRepository;
    private final UserBusinessRoleRepository userBusinessRoleRepository;
    private final RoleRepository roleRepository;
    private final BusinessRoleRepository businessRoleRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void assignRole(Long userId, Long roleId) {
        if (userRoleRepository.existsByIdUserIdAndIdRoleId(userId, roleId)) {
            throw new BusinessException("DUPLICATE", "User already has this role", HttpStatus.CONFLICT);
        }

        User user = getUser(userId);
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + roleId));

        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(userId, roleId))
                .user(user)
                .role(role)
                .build();

        userRoleRepository.save(userRole);
    }

    @Override
    @Transactional
    public void removeRole(Long userId, Long roleId) {
        UserRoleId key = new UserRoleId(userId, roleId);
        if (!userRoleRepository.existsById(key)) {
            throw new ResourceNotFoundException("User does not have this role");
        }
        userRoleRepository.deleteById(key);
    }

    @Override
    @Transactional
    public void assignBusinessRole(Long userId, Long businessRoleId) {
        if (userBusinessRoleRepository.existsByIdUserIdAndIdBusinessRoleId(userId, businessRoleId)) {
            throw new BusinessException("DUPLICATE", "User already has this business role", HttpStatus.CONFLICT);
        }

        User user = getUser(userId);
        BusinessRole businessRole = businessRoleRepository.findById(businessRoleId)
                .orElseThrow(() -> new ResourceNotFoundException("Business role not found with id: " + businessRoleId));

        UserBusinessRole ubr = UserBusinessRole.builder()
                .id(new UserBusinessRoleId(userId, businessRoleId))
                .user(user)
                .businessRole(businessRole)
                .build();

        userBusinessRoleRepository.save(ubr);
    }

    @Override
    @Transactional
    public void removeBusinessRole(Long userId, Long businessRoleId) {
        UserBusinessRoleId key = new UserBusinessRoleId(userId, businessRoleId);
        if (!userBusinessRoleRepository.existsById(key)) {
            throw new ResourceNotFoundException("User does not have this business role");
        }
        userBusinessRoleRepository.deleteById(key);
    }

    @Override
    public UserEffectiveRolesResponse getEffectiveRoles(Long userId) {
        User user = getUser(userId);

        Set<RoleResponse> directRoles = userRoleRepository.findByUserIdWithRole(userId).stream()
                .map(ur -> toRoleResponse(ur.getRole()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        List<UserBusinessRole> ubrs = userBusinessRoleRepository.findByUserIdWithRoles(userId);

        Set<BusinessRoleResponse> businessRoles = ubrs.stream()
                .map(ubr -> toBusinessRoleResponse(ubr.getBusinessRole()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<RoleResponse> derivedRoles = ubrs.stream()
                .flatMap(ubr -> ubr.getBusinessRole().getRoles().stream())
                .map(this::toRoleResponse)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<RoleResponse> effectiveRoles = new LinkedHashSet<>(directRoles);
        effectiveRoles.addAll(derivedRoles);

        return new UserEffectiveRolesResponse(
                user.getId(),
                user.getUsername(),
                directRoles,
                businessRoles,
                effectiveRoles
        );
    }

    private User getUser(Long userId) {
        return userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    private RoleResponse toRoleResponse(Role role) {
        return new RoleResponse(
                role.getId(), role.getName(), role.getDescription(),
                role.getActive(), role.getCreatedAt(), role.getUpdatedAt()
        );
    }

    private BusinessRoleResponse toBusinessRoleResponse(BusinessRole br) {
        Set<RoleResponse> roles = br.getRoles().stream()
                .map(this::toRoleResponse)
                .collect(Collectors.toSet());

        return new BusinessRoleResponse(
                br.getId(), br.getName(), br.getDescription(),
                br.getActive(), roles,
                br.getCreatedAt(), br.getUpdatedAt()
        );
    }
}

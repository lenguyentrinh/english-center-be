package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.Repository.BRoleRepository;
import com.trinh.english_center_be.modules.user.dto.BusinessRoleRequest;
import com.trinh.english_center_be.modules.user.dto.BusinessRoleResponse;
import com.trinh.english_center_be.modules.user.dto.RoleResponse;
import com.trinh.english_center_be.modules.user.entity.BusinessRole;
import com.trinh.english_center_be.shared.exception.BusinessException;
import com.trinh.english_center_be.shared.exception.ResourceNotFoundException;
import com.trinh.english_center_be.shared.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BRoleServiceImpl implements BRoleService {

    private final BRoleRepository bRoleRepository;

    @Override
    public List<BusinessRoleResponse> findAll() {
        return bRoleRepository.findByActiveTrue().stream().map(this::toResponse).toList();
    }

    @Override
    public BusinessRoleResponse findResponseById(Long id) {
        BusinessRole businessRole = findById(id);
        if (Boolean.FALSE.equals(businessRole.getActive())) {
            throw new ResourceNotFoundException(String.format(StringUtil.NOT_FOUND_BY_ID, StringUtil.BUSINESS, id));
        }
        return toResponse(businessRole);
    }

    @Override
    @Transactional
    public BusinessRoleResponse create(BusinessRoleRequest businessRoleRequest) {
        if (bRoleRepository.existsByName(businessRoleRequest.getName())) {
            throw new BusinessException(
                    String.format(StringUtil.ENTITY_ALREADY_EXISTS, StringUtil.BUSINESS),
                    HttpStatus.CONFLICT
            );
        }

        BusinessRole businessRole = BusinessRole.builder()
                .name(businessRoleRequest.getName())
                .description(businessRoleRequest.getDescription())
                .active(businessRoleRequest.getActive() != null ? businessRoleRequest.getActive() : true)
                .build();

        return toResponse(bRoleRepository.save(businessRole));
    }

    @Override
    public BusinessRole findById(Long id) {
        return bRoleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(StringUtil.NOT_FOUND_BY_ID, StringUtil.BUSINESS, id))
        );
    }

    @Override
    @Transactional
    public void save(BusinessRole businessRole) {
        bRoleRepository.save(businessRole);
    }

    @Override
    @Transactional
    public BusinessRoleResponse updateById(Long id, BusinessRoleRequest businessRoleRequest) {
        BusinessRole existing = findById(id);

        if (businessRoleRequest.getName() != null && !businessRoleRequest.getName().equals(existing.getName())) {
            if (bRoleRepository.existsByNameAndIdNot(businessRoleRequest.getName(), id)) {
                throw new BusinessException(
                        String.format(StringUtil.ENTITY_ALREADY_EXISTS, StringUtil.BUSINESS),
                        HttpStatus.CONFLICT
                );
            }
            existing.setName(businessRoleRequest.getName());
        }
        if (businessRoleRequest.getDescription() != null) {
            existing.setDescription(businessRoleRequest.getDescription());
        }
        if (businessRoleRequest.getActive() != null) {
            existing.setActive(businessRoleRequest.getActive());
        }

        return toResponse(bRoleRepository.save(existing));
    }

    @Override
    @Transactional
    public void softDeleteById(Long id) {
        BusinessRole existing = findById(id);
        existing.setActive(false);
        bRoleRepository.save(existing);
    }

    private BusinessRoleResponse toResponse(BusinessRole businessRole) {
        List<RoleResponse> roles = businessRole.getRoles() == null ? List.of() :
                businessRole.getRoles().stream()
                        .filter(r -> Boolean.TRUE.equals(r.getActive()))
                        .map(r -> RoleResponse.builder()
                                .id(r.getId())
                                .role(r.getRole())
                                .name(r.getName())
                                .description(r.getDescription())
                                .active(r.getActive())
                                .businessRoleId(businessRole.getId())
                                .businessRoleName(businessRole.getName())
                                .build())
                        .toList();

        return BusinessRoleResponse.builder()
                .id(businessRole.getId())
                .name(businessRole.getName())
                .description(businessRole.getDescription())
                .active(businessRole.getActive())
                .roles(roles)
                .createdAt(businessRole.getCreatedAt())
                .updateAt(businessRole.getUpdateAt())
                .build();
    }
}

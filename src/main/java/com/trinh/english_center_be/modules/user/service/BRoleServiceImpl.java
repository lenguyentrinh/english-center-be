package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.Repository.BRoleRepository;
import com.trinh.english_center_be.modules.user.dto.BusinessRoleRequest;
import com.trinh.english_center_be.modules.user.dto.BusinessRoleResponse;
import com.trinh.english_center_be.modules.user.entity.BusinessRole;
import com.trinh.english_center_be.shared.exception.ResourceNotFoundException;
import com.trinh.english_center_be.shared.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BRoleServiceImpl implements BRoleService {
    private final BRoleRepository bRoleRepository;

    @Override
    public List<BusinessRoleResponse> findAll() {
        return bRoleRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public BusinessRoleResponse findResponseById(Long id) {
        return toResponse(findById(id));
    }

    @Override
    public BusinessRoleResponse create(BusinessRoleRequest businessRoleRequest) {
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
    public void save(BusinessRole businessRole) {
        bRoleRepository.save(businessRole);
    }

    @Override
    public BusinessRoleResponse updateById(Long id, BusinessRoleRequest businessRoleRequest) {
        BusinessRole existing = findById(id);

        if (businessRoleRequest.getName() != null) {
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
    public void softDeleteById(Long id) {
        BusinessRole existing = findById(id);
        existing.setActive(false);
        bRoleRepository.save(existing);
    }

    private BusinessRoleResponse toResponse(BusinessRole businessRole) {
        return BusinessRoleResponse.builder()
                .id(businessRole.getId())
                .name(businessRole.getName())
                .description(businessRole.getDescription())
                .active(businessRole.getActive())
                .createdAt(businessRole.getCreatedAt())
                .build();
    }
}

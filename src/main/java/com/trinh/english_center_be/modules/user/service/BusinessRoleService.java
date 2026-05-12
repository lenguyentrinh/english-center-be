package com.trinh.english_center_be.modules.user.service;

import com.trinh.english_center_be.modules.user.dto.BusinessRoleRequest;
import com.trinh.english_center_be.modules.user.dto.BusinessRoleResponse;

import java.util.List;

public interface BusinessRoleService {
    List<BusinessRoleResponse> findAll();
    BusinessRoleResponse findById(Long id);
    BusinessRoleResponse create(BusinessRoleRequest request);
    BusinessRoleResponse update(Long id, BusinessRoleRequest request);
    void delete(Long id);
    BusinessRoleResponse addRoles(Long businessRoleId, List<Long> roleIds);
    BusinessRoleResponse removeRoles(Long businessRoleId, List<Long> roleIds);
}

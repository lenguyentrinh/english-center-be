package com.trinh.english_center_be.modules.user.controller;

import com.trinh.english_center_be.modules.user.dto.BusinessRoleRequest;
import com.trinh.english_center_be.modules.user.dto.BusinessRoleResponse;
import com.trinh.english_center_be.modules.user.service.BusinessRoleService;
import com.trinh.english_center_be.shared.response.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-roles")
@RequiredArgsConstructor
public class BusinessRoleController {

    private final BusinessRoleService businessRoleService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<BusinessRoleResponse>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Get all business roles successfully", businessRoleService.findAll())
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BusinessRoleResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Get business role successfully", businessRoleService.findById(id))
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BusinessRoleResponse>> create(
            @Valid @RequestBody BusinessRoleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Create business role successfully", businessRoleService.create(request)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BusinessRoleResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody BusinessRoleRequest request
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Update business role successfully", businessRoleService.update(id, request))
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        businessRoleService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Delete business role successfully", null));
    }

    @PostMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BusinessRoleResponse>> addRoles(
            @PathVariable Long id,
            @RequestBody List<Long> roleIds
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Roles added to business role successfully",
                        businessRoleService.addRoles(id, roleIds))
        );
    }

    @DeleteMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BusinessRoleResponse>> removeRoles(
            @PathVariable Long id,
            @RequestBody List<Long> roleIds
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Roles removed from business role successfully",
                        businessRoleService.removeRoles(id, roleIds))
        );
    }
}

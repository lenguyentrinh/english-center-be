package com.trinh.english_center_be.modules.user.controller;

import com.trinh.english_center_be.modules.user.dto.RoleRequest;
import com.trinh.english_center_be.modules.user.dto.RoleResponse;
import com.trinh.english_center_be.modules.user.service.RoleService;
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
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Get all roles successfully", roleService.findAll())
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<RoleResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Get role successfully", roleService.findById(id))
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<RoleResponse>> create(
            @Valid @RequestBody RoleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Create role successfully", roleService.create(request)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<RoleResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody RoleRequest request
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Update role successfully", roleService.update(id, request))
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Delete role successfully", null));
    }
}

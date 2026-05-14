package com.trinh.english_center_be.modules.academic.controller;

import com.trinh.english_center_be.modules.academic.dto.TeachingClassRequest;
import com.trinh.english_center_be.modules.academic.dto.TeachingClassResponse;
import com.trinh.english_center_be.modules.academic.service.TeachingClassService;
import com.trinh.english_center_be.shared.response.ApiResponse;
import com.trinh.english_center_be.shared.util.StringUtil;
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
@RequestMapping("/teaching-classes")
@RequiredArgsConstructor
public class TeachingClassController {

    private final TeachingClassService teachingClassService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TeachingClassResponse>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        StringUtil.CLASSES_RETRIEVED_SUCCESSFULLY,
                        teachingClassService.findAll()
                )
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeachingClassResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        String.format(StringUtil.INFORMATION_RETRIEVED_SUCCESSFULLY,id),
                        teachingClassService.findById(id)
                )
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeachingClassResponse>> create(
            @Valid @RequestBody TeachingClassRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        201,
                        StringUtil.INFORMATION_CREATED_SUCCESSFULLY,
                        teachingClassService.create(request)
                ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TeachingClassResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody TeachingClassRequest request
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        String.format(StringUtil.INFORMATION_UPDATED_SUCCESSFULLY,id),
                        teachingClassService.update(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        teachingClassService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        StringUtil.DELETED_SUCCESSFULLY,
                        null
                )
        );
    }
}

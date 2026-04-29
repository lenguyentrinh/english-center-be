package com.trinh.english_center_be.modules.academic.controller;

import com.trinh.english_center_be.modules.academic.dto.TeachingClassRequest;
import com.trinh.english_center_be.modules.academic.dto.TeachingClassResponse;
import com.trinh.english_center_be.modules.academic.service.TeachingClassService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TeachingClassResponse>> getAll() {
        return ResponseEntity.ok(teachingClassService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeachingClassResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teachingClassService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TeachingClassResponse> create(@RequestBody TeachingClassRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teachingClassService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeachingClassResponse> update(@PathVariable Long id, @RequestBody TeachingClassRequest request) {
        return ResponseEntity.ok(teachingClassService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teachingClassService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

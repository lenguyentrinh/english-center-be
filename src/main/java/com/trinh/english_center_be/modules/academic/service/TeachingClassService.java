package com.trinh.english_center_be.modules.academic.service;

import com.trinh.english_center_be.modules.academic.dto.TeachingClassRequest;
import com.trinh.english_center_be.modules.academic.dto.TeachingClassResponse;
import com.trinh.english_center_be.modules.academic.entity.TeachingClass;
import com.trinh.english_center_be.modules.academic.repository.TeachingClassRepository;
import com.trinh.english_center_be.shared.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeachingClassService {

    private final TeachingClassRepository teachingClassRepository;

    public List<TeachingClassResponse> findAll() {
        return teachingClassRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TeachingClassResponse findById(Long id) {
        TeachingClass teachingClass = teachingClassRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return toResponse(teachingClass);
    }

    public TeachingClassResponse create(TeachingClassRequest request) {
        TeachingClass teachingClass = TeachingClass.builder()
                .code(request.code())
                .name(request.name())
                .courseId(request.courseId())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .maxStudent(request.maxStudent())
                .status(request.status())
                .build();

        return toResponse(teachingClassRepository.save(teachingClass));
    }

    public TeachingClassResponse update(Long id, TeachingClassRequest request) {
        TeachingClass teachingClass = teachingClassRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        teachingClass.setCode(request.code());
        teachingClass.setName(request.name());
        teachingClass.setCourseId(request.courseId());
        teachingClass.setStartDate(request.startDate());
        teachingClass.setEndDate(request.endDate());
        teachingClass.setMaxStudent(request.maxStudent());
        teachingClass.setStatus(request.status());

        return toResponse(teachingClassRepository.save(teachingClass));
    }

    public void delete(Long id) {
        TeachingClass teachingClass = teachingClassRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        teachingClassRepository.delete(teachingClass);
    }

    private TeachingClassResponse toResponse(TeachingClass teachingClass) {
        return new TeachingClassResponse(
                teachingClass.getId(),
                teachingClass.getCode(),
                teachingClass.getName(),
                teachingClass.getCourseId(),
                teachingClass.getStartDate(),
                teachingClass.getEndDate(),
                teachingClass.getMaxStudent(),
                teachingClass.getStatus()
        );
    }
}

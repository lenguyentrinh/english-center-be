package com.trinh.english_center_be.modules.academic.dto;

import java.time.LocalDate;

public record TeachingClassResponse(
        Long id,
        String code,
        String name,
        Long courseId,
        LocalDate startDate,
        LocalDate endDate,
        Integer maxStudent,
        String status
) {
}

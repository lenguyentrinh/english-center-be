package com.trinh.english_center_be.modules.academic.dto;

import java.time.LocalDate;

public record TeachingClassRequest(
        String code,
        String name,
        Long courseId,
        LocalDate startDate,
        LocalDate endDate,
        Integer maxStudent,
        String status
) {
}

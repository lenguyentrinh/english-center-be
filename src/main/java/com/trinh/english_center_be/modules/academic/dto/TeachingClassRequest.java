package com.trinh.english_center_be.modules.academic.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record TeachingClassRequest(

        @NotBlank(message = "Code must not be blank")
        @Size(max = 50, message = "Code must be at most 50 characters")
        String code,

        @NotBlank(message = "Name must not be blank")
        @Size(max = 255, message = "Name must be at most 255 characters")
        String name,

        @NotNull(message = "CourseId must not be null")
        Long courseId,

        @NotNull(message = "Start date must not be null")
        LocalDate startDate,

        @NotNull(message = "End date must not be null")
        LocalDate endDate,

        @NotNull(message = "Max student must not be null")
        @Min(value = 1, message = "Max student must be at least 1")
        Integer maxStudent,

        @NotBlank(message = "Status must not be blank")
        @Pattern(regexp = "OPEN|ACTIVE|CLOSED", message = "Status must be OPEN, ACTIVE, or CLOSED")
        String status
) {
}
package com.trinh.english_center_be.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class BaseEntity {
    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updateAt;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
}

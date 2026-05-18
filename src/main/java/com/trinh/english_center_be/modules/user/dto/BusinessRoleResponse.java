package com.trinh.english_center_be.modules.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class BusinessRoleResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private List<RoleResponse> roles;
    private LocalDate createdAt;
    private LocalDate updateAt;
}

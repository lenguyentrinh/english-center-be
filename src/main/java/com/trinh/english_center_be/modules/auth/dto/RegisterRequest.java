package com.trinh.english_center_be.modules.auth.dto;

public record RegisterRequest(
        String username,
        String password,
        String email
) {}

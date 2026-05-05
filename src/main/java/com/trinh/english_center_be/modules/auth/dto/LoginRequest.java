package com.trinh.english_center_be.modules.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
   @NotBlank String username,
   @NotBlank String password
) {}

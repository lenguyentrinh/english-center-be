package com.trinh.english_center_be.modules.auth.dto;

public record LoginRequest(
   String username,
   String password
) {}

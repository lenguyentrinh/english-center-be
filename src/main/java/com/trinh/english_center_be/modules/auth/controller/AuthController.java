package com.trinh.english_center_be.modules.auth.controller;

import com.trinh.english_center_be.modules.auth.dto.LoginRequest;
import com.trinh.english_center_be.modules.auth.dto.SignupRequest;
import com.trinh.english_center_be.modules.auth.service.AuthService;
import com.trinh.english_center_be.shared.response.ApiResponse;
import jakarta.validation.Valid;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @Value("${cookie.secure:true}")
    private boolean cookieSecure;

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@RequestBody @Valid SignupRequest request){
        authService.signup(request);

        return ResponseEntity.ok()
                .body(new ApiResponse<>(200,"Signup successful",null));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> login(@RequestBody @Valid LoginRequest request){
        String token = authService.authenticate(request);

        ResponseCookie cookie = ResponseCookie.from("AUTH_TOKEN", token)
                .httpOnly(true)
                .secure(cookieSecure)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofMillis(jwtExpirationMs))
                .build();


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new ApiResponse<>(200,"Login successful",null));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(){
        ResponseCookie cookie = ResponseCookie.from("AUTH_TOKEN", "")
                .httpOnly(true)
                .secure(cookieSecure)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new ApiResponse<>(200, "Logout successful", null));
    }
}
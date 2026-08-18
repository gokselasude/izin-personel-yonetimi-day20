package com.example.izin_personel_yonetimi.controller;

import com.example.izin_personel_yonetimi.dto.AuthResponse;
import com.example.izin_personel_yonetimi.dto.LoginRequest;
import com.example.izin_personel_yonetimi.entity.AppUser;
import com.example.izin_personel_yonetimi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<AppUser> getCurrentUser(@AuthenticationPrincipal AppUser user) {
        return ResponseEntity.ok(user);
    }
}
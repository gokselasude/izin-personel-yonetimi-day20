package com.example.izin_personel_yonetimi.service;

import com.example.izin_personel_yonetimi.dto.AuthResponse;
import com.example.izin_personel_yonetimi.dto.LoginRequest;
import com.example.izin_personel_yonetimi.entity.AppUser;
import com.example.izin_personel_yonetimi.repository.AppUserRepository;
import com.example.izin_personel_yonetimi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request) {
        // Kullanıcıyı veritabanında ara
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        // Şifre kontrolü
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Hatalı şifre!");
        }

        // JWT token üret
        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}
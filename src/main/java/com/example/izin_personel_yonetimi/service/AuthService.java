package com.example.izin_personel_yonetimi.service;

import com.example.izin_personel_yonetimi.dto.AuthResponse;
import com.example.izin_personel_yonetimi.dto.LoginRequest;
import com.example.izin_personel_yonetimi.entity.AppUser;
import com.example.izin_personel_yonetimi.entity.Role;
import com.example.izin_personel_yonetimi.repository.AppUserRepository;
import com.example.izin_personel_yonetimi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.izin_personel_yonetimi.entity.Employee;
import com.example.izin_personel_yonetimi.repository.EmployeeRepository;
import com.example.izin_personel_yonetimi.entity.LeaveType;
import com.example.izin_personel_yonetimi.repository.LeaveTypeRepository;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().name());
        String jwtToken = jwtService.generateToken(extraClaims, user);
        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
    @Bean
    CommandLineRunner initUser(
            AppUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            EmployeeRepository employeeRepository,
            LeaveTypeRepository leaveTypeRepository
    ) {
        return args -> {
            AppUser user = userRepository.findByUsername("gokselasude").orElse(new AppUser());
            user.setUsername("gokselasude");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            System.out.println("Test kullanıcısı güncellendi/oluşturuldu: gokselasude / 123456");

            if (employeeRepository.findByUser_Username("gokselasude").isEmpty()) {
                Employee employee = new Employee("Göksel", "Asude", "gokselasude@example.com");
                employee.setUser(user);
                employeeRepository.save(employee);
                System.out.println("Test personeli oluşturuldu: gokselasude");
            }

            if (leaveTypeRepository.count() == 0) {
                LeaveType yillik = new LeaveType();
                yillik.setName("Yıllık İzin");
                yillik.setDescription("Yıllık izin hakkı");
                yillik.setDefaultDays(14);
                leaveTypeRepository.save(yillik);

                LeaveType mazeret = new LeaveType();
                mazeret.setName("Mazeret İzni");
                mazeret.setDescription("Mazeret izni");
                mazeret.setDefaultDays(3);
                leaveTypeRepository.save(mazeret);

                LeaveType hastalik = new LeaveType();
                hastalik.setName("Hastalık İzni");
                hastalik.setDescription("Hastalık izni");
                hastalik.setDefaultDays(7);
                leaveTypeRepository.save(hastalik);
            }
        };
    }
}
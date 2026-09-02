package com.example.izin_personel_yonetimi.repository;

import com.example.izin_personel_yonetimi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUser_Username(String username);
}
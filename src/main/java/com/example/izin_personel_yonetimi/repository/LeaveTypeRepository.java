package com.example.izin_personel_yonetimi.repository;

import com.example.izin_personel_yonetimi.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
}
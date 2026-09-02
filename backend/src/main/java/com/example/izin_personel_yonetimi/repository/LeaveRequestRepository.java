package com.example.izin_personel_yonetimi.repository;

import com.example.izin_personel_yonetimi.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee_Id(Long employeeId);
}
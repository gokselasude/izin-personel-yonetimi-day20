package com.example.izin_personel_yonetimi.service;
import com.example.izin_personel_yonetimi.dto.LeaveRequestResponse;
import java.util.List;
import java.util.stream.Collectors;
import com.example.izin_personel_yonetimi.dto.LeaveRequestResponse;
import com.example.izin_personel_yonetimi.dto.LeaveRequestDto;
import com.example.izin_personel_yonetimi.entity.Employee;
import com.example.izin_personel_yonetimi.entity.LeaveRequest;
import com.example.izin_personel_yonetimi.entity.LeaveType;
import com.example.izin_personel_yonetimi.repository.EmployeeRepository;
import com.example.izin_personel_yonetimi.repository.LeaveRequestRepository;
import com.example.izin_personel_yonetimi.repository.LeaveTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveRequestService(
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository,
            LeaveTypeRepository leaveTypeRepository
    ) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.leaveTypeRepository = leaveTypeRepository;
    }

    public LeaveRequest createLeaveRequest(String username, LeaveRequestDto dto) {
        Employee employee = employeeRepository.findByUser_Username(username)
                .orElseThrow(() -> new RuntimeException("Bu kullanıcıya bağlı personel kaydı bulunamadı: " + username));

        LeaveType leaveType = leaveTypeRepository.findById(dto.getLeaveTypeId())
                .orElseThrow(() -> new RuntimeException("İzin türü bulunamadı: " + dto.getLeaveTypeId()));

        LeaveRequest leaveRequest = new LeaveRequest(
                employee,
                leaveType,
                dto.getStartDate(),
                dto.getEndDate(),
                "PENDING"
        );

        return leaveRequestRepository.save(leaveRequest);
    }
    public List<LeaveRequestResponse> getAllLeaveRequests() {
        return leaveRequestRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public LeaveRequest approveLeaveRequest(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("İzin talebi bulunamadı: " + id));
        leaveRequest.setStatus("APPROVED");
        return leaveRequestRepository.save(leaveRequest);
    }

    public LeaveRequest rejectLeaveRequest(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("İzin talebi bulunamadı: " + id));
        leaveRequest.setStatus("REJECTED");
        return leaveRequestRepository.save(leaveRequest);
    }

    private LeaveRequestResponse toResponse(LeaveRequest lr) {
        String employeeName = lr.getEmployee().getFirstName() + " " + lr.getEmployee().getLastName();
        return new LeaveRequestResponse(
                lr.getId(),
                employeeName,
                lr.getLeaveType().getName(),
                lr.getStartDate(),
                lr.getEndDate(),
                lr.getStatus()
        );
    }
}
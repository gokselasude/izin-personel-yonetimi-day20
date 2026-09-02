package com.example.izin_personel_yonetimi.controller;

import com.example.izin_personel_yonetimi.dto.LeaveRequestDto;
import com.example.izin_personel_yonetimi.dto.LeaveRequestResponse;
import com.example.izin_personel_yonetimi.entity.LeaveRequest;
import com.example.izin_personel_yonetimi.service.LeaveRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public ResponseEntity<LeaveRequestResponse> createLeaveRequest(
            @RequestBody LeaveRequestDto dto,
            Authentication authentication
    ) {
        String username = authentication.getName();
        LeaveRequest created = leaveRequestService.createLeaveRequest(username, dto);

        LeaveRequestResponse response = new LeaveRequestResponse(
                created.getId(),
                created.getEmployee().getFirstName() + " " + created.getEmployee().getLastName(),
                created.getLeaveType().getName(),
                created.getStartDate(),
                created.getEndDate(),
                created.getStatus()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequestResponse>> getAllLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approveLeaveRequest(@PathVariable Long id) {
        leaveRequestService.approveLeaveRequest(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> rejectLeaveRequest(@PathVariable Long id) {
        leaveRequestService.rejectLeaveRequest(id);
        return ResponseEntity.ok().build();
    }
}
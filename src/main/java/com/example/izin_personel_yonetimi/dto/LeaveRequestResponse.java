package com.example.izin_personel_yonetimi.dto;

import java.time.LocalDate;

public class LeaveRequestResponse {
    private Long id;
    private String employeeName;
    private String leaveTypeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public LeaveRequestResponse(Long id, String employeeName, String leaveTypeName, LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.employeeName = employeeName;
        this.leaveTypeName = leaveTypeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getEmployeeName() { return employeeName; }
    public String getLeaveTypeName() { return leaveTypeName; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getStatus() { return status; }
}
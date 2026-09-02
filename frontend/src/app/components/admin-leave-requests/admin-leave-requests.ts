import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LeaveRequestService } from '../../services/leave-request.service';

@Component({
  selector: 'app-admin-leave-requests',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-leave-requests.html',
  styleUrl: './admin-leave-requests.css',
})
export class AdminLeaveRequests implements OnInit {
  leaveRequests: any[] = [];
  loading = true;

  constructor(
    private leaveRequestService: LeaveRequestService,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.loadRequests();
  }

  loadRequests(): void {
    this.loading = true;
    this.leaveRequestService.getAllLeaveRequests().subscribe({
      next: (data) => {
        this.leaveRequests = data;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Talepler yüklenemedi:', err);
        this.loading = false;
        this.cdr.detectChanges();
      },
    });
  }

  approve(id: number): void {
    this.leaveRequestService.approveLeaveRequest(id).subscribe({
      next: () => this.loadRequests(),
      error: (err) => console.error('Onaylanamadı:', err),
    });
  }

  reject(id: number): void {
    this.leaveRequestService.rejectLeaveRequest(id).subscribe({
      next: () => this.loadRequests(),
      error: (err) => console.error('Reddedilemedi:', err),
    });
  }
}

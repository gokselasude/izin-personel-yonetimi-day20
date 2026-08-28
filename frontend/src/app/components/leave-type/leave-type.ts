import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LeaveTypeService } from '../../services/leave-type.service';

@Component({
  selector: 'app-leave-type',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './leave-type.html',
  styleUrl: './leave-type.css',
})
export class LeaveTypeComponent implements OnInit {
  leaveTypes: any[] = [];
  newLeaveType: any = { name: '', description: '' };

  constructor(private leaveTypeService: LeaveTypeService) {}

  ngOnInit(): void {
    this.loadLeaveTypes();
  }

  loadLeaveTypes(): void {
    this.leaveTypeService.getLeaveTypes().subscribe({
      next: (data: any) => {
        this.leaveTypes = data;
      },
      error: (err: any) => {
        console.error('İzin türleri yüklenirken hata oluştu:', err);
      },
    });
  }

  createLeaveType(): void {
    this.leaveTypeService.createLeaveType(this.newLeaveType).subscribe({
      next: () => {
        this.newLeaveType = { name: '', description: '' };
        this.loadLeaveTypes();
      },
      error: (err: any) => {
        console.error('İzin türü eklenirken hata oluştu:', err);
      },
    });
  }
}

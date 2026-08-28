import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-leave-request',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  template: `
    <div class="container mt-4">
      <h2>İzin Talebi Oluştur</h2>
      <form [formGroup]="leaveForm" (ngSubmit)="onSubmit()">
        <div class="mb-3">
          <label class="form-label">İzin Türü</label>
          <select class="form-select" formControlName="leaveTypeId">
            <option value="">Seçiniz</option>
            <option value="1">Yıllık İzin</option>
            <option value="2">Mazeret İzni</option>
            <option value="3">Hastalık İzni</option>
          </select>
        </div>

        <div class="mb-3">
          <label class="form-label">Başlangıç Tarihi</label>
          <input type="date" class="form-control" formControlName="startDate" />
        </div>

        <div class="mb-3">
          <label class="form-label">Bitiş Tarihi</label>
          <input type="date" class="form-control" formControlName="endDate" />
        </div>

        <div class="mb-3">
          <label class="form-label">Açıklama</label>
          <textarea class="form-control" formControlName="description" rows="3"></textarea>
        </div>

        <button type="submit" class="btn btn-primary" [disabled]="leaveForm.invalid">
          Talebi Gönder
        </button>
      </form>
    </div>
  `,
})
export class LeaveRequestComponent implements OnInit {
  leaveForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.leaveForm = this.fb.group({
      leaveTypeId: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      description: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  onSubmit(): void {
    if (this.leaveForm.valid) {
      console.log('İzin talebi gönderildi:', this.leaveForm.value);
    }
  }
}

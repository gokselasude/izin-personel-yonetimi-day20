import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DepartmentService } from '../../services/department.service';

@Component({
  selector: 'app-department',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './department.html',
  styleUrls: ['./department.css'],
})
export class DepartmentComponent implements OnInit {
  departments: any[] = [];
  newDepartment: any = { name: '', description: '' };

  constructor(private departmentService: DepartmentService) {}

  ngOnInit(): void {
    this.loadDepartments();
  }

  // Departmanları listeleme
  loadDepartments(): void {
    this.departmentService.getDepartments().subscribe({
      next: (data) => {
        this.departments = data;
      },
      error: (err) => {
        console.error('Departmanlar yüklenirken hata oluştu:', err);
      },
    });
  }

  // Yeni departman ekleme
  createDepartment(): void {
    this.departmentService.createDepartment(this.newDepartment).subscribe({
      next: (res) => {
        // Formu temizle ve listeyi yenile
        this.newDepartment = { name: '', description: '' };
        this.loadDepartments();
      },
      error: (err) => {
        console.error('Departman eklenirken hata oluştu:', err);
      },
    });
  }
}

import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { LeaveRequestComponent } from './components/leave-request/leave-request.component';
import { LeaveTypeComponent } from './components/leave-type/leave-type.component';
import { DepartmentComponent } from './components/department/department.component';
import { EmployeeComponent } from './components/employee/employee';
import { authGuard } from './auth.guard';

export const routes: Routes = [
  { path: 'leave-types', component: LeaveTypeComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'leave-request',
    component: LeaveRequestComponent,
    canActivate: [authGuard],
  },
  {
    path: 'dashboard',
    component: LeaveRequestComponent,
    canActivate: [authGuard],
  },
  {
    path: 'departments',
    component: DepartmentComponent,
    canActivate: [authGuard],
  },
  {
    path: 'employees',
    component: EmployeeComponent,
    canActivate: [authGuard],
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' },
];

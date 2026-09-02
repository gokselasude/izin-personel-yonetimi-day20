import { Routes } from '@angular/router';
import { authGuard } from './auth.guard';
import { LoginComponent } from './components/login/login';
import { LeaveRequestComponent } from './leave-request';
import { LeaveTypeComponent } from './components/leave-type/leave-type';
import { DepartmentComponent } from './components/department/department';
import { AdminLeaveRequests } from './components/admin-leave-requests/admin-leave-requests';
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
    path: 'admin-leave-requests',
    component: AdminLeaveRequests,
    canActivate: [authGuard],
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' },
];

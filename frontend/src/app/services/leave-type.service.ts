import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LeaveTypeService {
  private apiUrl: string = 'http://localhost:8080/api/leave-types';

  constructor(private http: HttpClient) {}

  getLeaveTypes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  createLeaveType(leaveType: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, leaveType);
  }

  deleteLeaveType(id: number): Observable<any> {
    return this.http.delete<any>(this.apiUrl + '/' + id);
  }
}

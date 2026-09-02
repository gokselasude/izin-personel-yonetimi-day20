import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LeaveRequestService {
  private apiUrl = 'http://localhost:8080/api/leave-requests';

  constructor(private http: HttpClient) {}

  createLeaveRequest(data: any): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }

  getAllLeaveRequests(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  approveLeaveRequest(id: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/approve`, {});
  }

  rejectLeaveRequest(id: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/reject`, {});
  }
}

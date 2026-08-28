import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(credentials: { username: string; password: string }): Observable<any> {
    return this.http.post(this.apiUrl + '/login', credentials);
  }
  // Token'ı tarayıcının hafızasına kaydeder
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // Hafızadaki token'ı getirir (ileride istek atarken lazım olacak)
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Çıkış yapıldığında token'ı siler
  logout(): void {
    localStorage.removeItem('token');
  }
}

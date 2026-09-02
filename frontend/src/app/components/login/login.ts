import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginComponent {
  loginData = {
    username: '',
    password: '',
  };

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  onSubmit() {
    this.authService.login(this.loginData).subscribe({
      next: (response: any) => {
        console.log('Giriş başarılı:', response);

        // 1. Backend'den gelen token'ı alıp hafızaya (localStorage) kaydediyoruz
        this.authService.saveToken(response.token);

        // 2. Role'e göre yönlendirme
        if (response.role === 'ADMIN') {
          this.router.navigate(['/admin-leave-requests']);
        } else {
          this.router.navigate(['/leave-request']);
        }
      },
      error: (err) => {
        console.error('Giriş başarısız:', err);
        alert('Kullanıcı adı veya şifre hatalı!');
      },
    });
  }
}

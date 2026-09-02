import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError((error) => {
      if (error.status === 401) {
        // Oturum süresi dolmuşsa veya yetkisizse logine at
        router.navigate(['/login']);
      } else if (error.status === 500) {
        alert('Sunucu tarafında bir hata oluştu! Lütfen tekrar deneyin.');
      }
      return throwError(() => error);
    }),
  );
};

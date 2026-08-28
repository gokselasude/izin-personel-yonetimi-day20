import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const token = localStorage.getItem('token');

  if (token) {
    return true; // Token varsa geçişe izin ver
  }

  // Token yoksa login sayfasına yönlendir
  router.navigate(['/login']);
  return false;
};

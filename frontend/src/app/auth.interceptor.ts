import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // localStorage'dan giriş yaparken kaydettiğimiz token'ı alıyoruz
  const token = localStorage.getItem('token');

  if (token) {
    // Eğer token varsa, isteğin kopyasına Authorization header'ı ekliyoruz
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(cloned);
  }

  return next(req);
};

import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';

interface LoginResponse { token: string; roles: string[]; }

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly endpoint = 'http://localhost:8080/api/auth/login';
  readonly token = signal(localStorage.getItem('loan_token'));
  readonly roles = signal<string[]>(JSON.parse(localStorage.getItem('loan_roles') ?? '[]'));

  constructor(private readonly http: HttpClient) {}

  login(username: string, password: string) {
    return this.http.post<LoginResponse>(this.endpoint, { username, password }).pipe(
      tap(({ token, roles }) => {
        localStorage.setItem('loan_token', token);
        localStorage.setItem('loan_roles', JSON.stringify(roles));
        this.token.set(token);
        this.roles.set(roles);
      })
    );
  }

  logout() {
    localStorage.removeItem('loan_token');
    localStorage.removeItem('loan_roles');
    this.token.set(null);
    this.roles.set([]);
  }

  isAdmin() { return this.roles().includes('ROLE_ADMIN'); }
}

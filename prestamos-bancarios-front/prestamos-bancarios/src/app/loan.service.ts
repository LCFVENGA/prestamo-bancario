import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loan } from './loan.types';

@Injectable({ providedIn: 'root' })
export class LoanService {
  private readonly endpoint = 'http://localhost:8080/api/prestamos';
  constructor(private readonly http: HttpClient) {}
  mine() { return this.http.get<Loan[]>(`${this.endpoint}/me`); }
  all() { return this.http.get<Loan[]>(this.endpoint); }
  request(loan: Pick<Loan, 'userId' | 'amount' | 'termMonths'>) { return this.http.post<Loan>(`${this.endpoint}/prestamo`, loan); }
  approve(id: number) { return this.http.put<Loan>(`${this.endpoint}/aprobar/${id}`, {}); }
  reject(id: number) { return this.http.put<Loan>(`${this.endpoint}/rechazar/${id}`, {}); }
}

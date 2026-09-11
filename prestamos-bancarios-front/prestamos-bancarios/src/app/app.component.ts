import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from './auth.service';
import { LoanService } from './loan.service';
import { Loan } from './loan.types';
@Component({ selector: 'app-root', standalone: true, imports: [CommonModule, ReactiveFormsModule], templateUrl: './app.component.html', styleUrl: './app.component.css' })
export class AppComponent implements OnInit {
  readonly auth = inject(AuthService); private readonly loansApi = inject(LoanService); private readonly fb = inject(FormBuilder);
  loans: Loan[] = []; busy = false; error = ''; message = '';
  loginForm = this.fb.nonNullable.group({ username: ['', Validators.required], password: ['', Validators.required] });
  loanForm = this.fb.nonNullable.group({ amount: [0, [Validators.required, Validators.min(1)]], termMonths: [12, [Validators.required, Validators.min(1), Validators.max(120)]] });
  ngOnInit() { if (this.auth.token()) this.load(); }
  login() { if (this.loginForm.invalid) return; this.busy = true; this.error = ''; this.auth.login(this.loginForm.value.username!, this.loginForm.value.password!).subscribe({ next: () => { this.busy = false; this.load(); }, error: () => { this.busy = false; this.error = 'Credenciales inválidas.'; } }); }
  load() { const request = this.auth.isAdmin() ? this.loansApi.all() : this.loansApi.mine(); request.subscribe({ next: loans => this.loans = loans, error: () => this.error = 'No se pudo cargar la información.' }); }
  requestLoan() { if (this.loanForm.invalid) return; this.busy = true; this.loansApi.request({ userId: 0, amount: this.loanForm.value.amount!, termMonths: this.loanForm.value.termMonths! }).subscribe({ next: () => { this.busy = false; this.message = 'Solicitud enviada correctamente.'; this.loanForm.reset({ amount: 0, termMonths: 12 }); this.load(); }, error: () => { this.busy = false; this.error = 'No se pudo enviar la solicitud.'; } }); }
  change(id: number, approve: boolean) { (approve ? this.loansApi.approve(id) : this.loansApi.reject(id)).subscribe(() => this.load()); }
  logout() { this.auth.logout(); this.loans = []; }
}
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'prestamos-bancarios';
}

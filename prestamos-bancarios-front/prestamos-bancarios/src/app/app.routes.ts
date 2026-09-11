import { Routes } from '@angular/router';
import { authGuard, adminGuard } from './auth.guard';

export const routes: Routes = [
	{ path: 'user', loadComponent: () => import('./app.component').then(module => module.AppComponent), canActivate: [authGuard] },
	{ path: 'admin', loadComponent: () => import('./app.component').then(module => module.AppComponent), canActivate: [adminGuard] }
];

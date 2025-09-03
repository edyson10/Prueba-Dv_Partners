import { Routes } from '@angular/router';
import { ScoreGuard } from './core/guards/score.guard';
import { HomeComponent } from './features/home-component/home-component';
import { UserDetailComponent } from './features/user-detail-component/user-detail-component';

export const routes: Routes = [
  { path: '', component: HomeComponent, title: 'Buscar usuarios - DV Partners' },
  {
    path: 'user/:login',
    component: UserDetailComponent,
    canActivate: [ScoreGuard],
    title: 'Perfil de usuario',
  },
  { path: '**', redirectTo: '' },
];

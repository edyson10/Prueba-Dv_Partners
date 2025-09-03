import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { GitHubUserDetail } from '../../core/models/github.models';
import { ErrorService } from '../../core/service/error-service';
import { GithubService } from '../../core/service/github-service';
import { NgIf, Location } from '@angular/common';

@Component({
  selector: 'app-user-detail-component',
  imports: [CommonModule, NgIf, RouterLink],
  templateUrl: './user-detail-component.html',
  styleUrl: './user-detail-component.css',
})
export class UserDetailComponent {
  user?: GitHubUserDetail;
  loading = true;

  constructor(
    private route: ActivatedRoute,
    private api: GithubService,
    private err: ErrorService,
    private location: Location,
    private router: Router
  ) {
    const login = this.route.snapshot.paramMap.get('login')!;
    this.api.getUser(login).subscribe({
      next: (u) => {
        this.user = u;
        this.loading = false;
      },
      error: () => {
        this.err.show('No se pudo cargar el perfil.');
        this.loading = false;
      },
    });
  }

  goBack() {
    // Si hay historial, vuelve atrás; si no, navega al Home
    if (history.length > 1) this.location.back();
    else this.router.navigateByUrl('/');
  }
}

import { CommonModule, NgIf, NgFor } from '@angular/common';
import { Component, signal } from '@angular/core';
import {
  ReactiveFormsModule,
  FormControl,
  AbstractControl,
  ValidationErrors,
  ValidatorFn
} from '@angular/forms';
import { RouterLink } from '@angular/router';
import { GitHubSearchItem, GitHubUserDetail } from '../../core/models/github.models';
import { ErrorService } from '../../core/service/error-service';
import { GithubService } from '../../core/service/github-service';

// 👇 Import de Chart: tipos desde 'chart.js' y registro automático desde 'chart.js/auto'
import 'chart.js/auto';
import { Chart, ChartConfiguration } from 'chart.js';

function specialWordValidator(): ValidatorFn {
  return (control: AbstractControl<string | null>): ValidationErrors | null => {
    const v = (control.value ?? '').trim();
    if (!v) return { required: true };
    if (v.toLowerCase() === 'doublevpartners') return null;
    if (v.length >= 4) return null;
    // Forma estándar del error minlength
    return { minlength: { requiredLength: 4, actualLength: v.length } };
  };
}

@Component({
  selector: 'app-home-component',
  imports: [CommonModule, ReactiveFormsModule, NgIf, NgFor, RouterLink],
  templateUrl: './home-component.html',
  styleUrl: './home-component.css'
})
export class HomeComponent {

  q = new FormControl<string>('', {
    nonNullable: true,
    validators: [specialWordValidator()],
  });

  loading = signal(false);
  results = signal<GitHubSearchItem[]>([]);
  total = signal(0);

  private chart?: Chart<'bar'>;

  constructor(private api: GithubService, private err: ErrorService) {}

  async searchPromise() {
    try {
      this.loading.set(true);
      const res = await this.api.searchUsersPromise(this.q.value);
      this.total.set(res.total_count);
      this.results.set(res.items.slice(0, 10));
      this.renderChartFromResults();
    } catch {
      this.err.show('Error buscando usuarios.');
    } finally {
      this.loading.set(false);
    }
  }

  searchObservable() {
    if (this.q.invalid) {
      this.err.show('La búsqueda debe tener mínimo 4 caracteres o ser "doublevpartners".');
      this.results.set([]);
      this.total.set(0);
      this.destroyChart();
      return;
    }
    this.loading.set(true);
    this.api.searchUsers(this.q.value).subscribe({
      next: (res) => {
        this.total.set(res.total_count);
        const top10 = res.items.slice(0, 10);
        this.results.set(top10);
        this.renderChartFromResults();
      },
      error: () => this.err.show('Error buscando usuarios.'),
      complete: () => this.loading.set(false),
    });
  }

  private renderChartFromResults() {
    const items = this.results();
    if (!items.length) { this.destroyChart(); return; }

    this.api.getTopUsersDetailsForChart(items, 10).subscribe({
      next: (details: GitHubUserDetail[]) => {
        const labels = details.map(d => d.login);
        const data = details.map(d => d.followers);
        this.drawBarChart(labels, data);
      },
      error: () => this.err.show('No se pudo construir el gráfico.'),
    });
  }

  private drawBarChart(labels: string[], data: number[]) {
    const canvas = document.getElementById('followersChart') as HTMLCanvasElement | null;
    if (!canvas) return;
    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    this.destroyChart();

    const config: ChartConfiguration<'bar'> = {
      type: 'bar',
      data: { labels, datasets: [{ label: 'Seguidores', data }] },
      options: { responsive: true, maintainAspectRatio: false },
    };

    this.chart = new Chart(ctx, config);
  }

  private destroyChart() {
    if (this.chart) { this.chart.destroy(); this.chart = undefined; }
  }
}

import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorService } from '../../core/service/error-service';

@Component({
  selector: 'app-error-banner-component',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './error-banner-component.html',
  styleUrl: './error-banner-component.css',
})
export class ErrorBannerComponent {
  private readonly err = inject(ErrorService);
  readonly msg = this.err.message;

  close() {
    this.err.clear();
  }
}

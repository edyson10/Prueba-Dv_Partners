import 'zone.js';
import { bootstrapApplication, provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient /* , withFetch, withInterceptorsFromDi */ } from '@angular/common/http';
import { provideRouter, withInMemoryScrolling } from '@angular/router';
import { App } from './app/app';
import { routes } from './app/app.routes';
import { appConfig } from './app/app.config';

bootstrapApplication(App, {
  providers: [...appConfig.providers, provideClientHydration()],
}).catch(err => console.error(err));


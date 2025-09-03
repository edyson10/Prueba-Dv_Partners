import { Injectable } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { map, catchError, of } from 'rxjs';
import { GithubService } from '../service/github-service';

export const ScoreGuard: CanActivateFn = (route) => {
  const login = route.paramMap.get('login') || '';
  const github = inject(GithubService);
  const router = inject(Router);

  // Puedes cambiarlo al numero 30 para que solo muestre el perfio de los que tenga el score más de 30
  var score = 30.0

  const cached = github.scoreCache.get(login);
  if (typeof cached === 'number') {
    if (cached >= score) return true;
    router.navigateByUrl('/');
    return false;
  }

  return github.searchUsers(login).pipe(
    map(res => {
      const item = res.items.find(i => i.login.toLowerCase() === login.toLowerCase());
      if (item && item.score >= score) return true;
      router.navigateByUrl('/');
      return false;
    }),
    catchError(() => {
      router.navigateByUrl('/');
      return of(false);
    })
  );
};
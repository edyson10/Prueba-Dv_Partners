import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, forkJoin } from 'rxjs';
import { GitHubSearchResponse, GitHubUserDetail, GitHubSearchItem } from '../models/github.models';

@Injectable({
  providedIn: 'root',
})
export class GithubService {
  private readonly base = 'https://api.github.com';

  scoreCache = new Map<string, number>();

  constructor(private http: HttpClient) {}

  // Metodo usando Observable
  searchUsers(q: string): Observable<GitHubSearchResponse> {
    const params = new HttpParams().set('q', q);
    return this.http.get<GitHubSearchResponse>(`${this.base}/search/users`, { params }).pipe(
      map((res) => {
        res.items?.forEach((it) => this.scoreCache.set(it.login, it.score));
        return res;
      })
    );
  }

  // Metodo usando promesas
  async searchUsersPromise(q: string): Promise<GitHubSearchResponse> {
    const params = new HttpParams().set('q', q);
    const res = await this.http
      .get<GitHubSearchResponse>(`${this.base}/search/users`, { params })
      .toPromise();
    res?.items?.forEach((it) => this.scoreCache.set(it.login, it.score));
    return res as GitHubSearchResponse;
  }

  getUser(login: string): Observable<GitHubUserDetail> {
    return this.http.get<GitHubUserDetail>(`${this.base}/users/${login}`);
  }

  // Obtiene detalles de los primeros N usuarios para el gráfico (followers)
  getTopUsersDetailsForChart(items: GitHubSearchItem[], top = 10): Observable<GitHubUserDetail[]> {
    const first = items.slice(0, top);
    return forkJoin(first.map((it) => this.getUser(it.login)));
  }
}

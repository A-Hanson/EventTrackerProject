import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { TvWatchingSession } from '../models/tv-watching-session';

@Injectable({
  providedIn: 'root'
})
export class TvwatchingsessionService {

  private baseUrl = 'http://localhost:8084//';
  private url = this.baseUrl + "api/tv_watching_sessions";

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<TvWatchingSession[]> {
    return this.http.get<TvWatchingSession[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.error("Service.index() error: " + err);
        return throwError(err);
      })
    );
  }
}

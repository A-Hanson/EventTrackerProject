import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { TvWatchingSession } from '../models/tv-watching-session';

@Injectable({
  providedIn: 'root'
})
export class TvwatchingsessionService {

  private baseUrl = 'http://localhost:8084/';
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
  show(sessionId): Observable<TvWatchingSession> {
    return this.http.get<TvWatchingSession>(this.url + '/' + sessionId)
      .pipe(
        catchError((err: any) => {
          console.error("Service.index() error: " + err);
          return throwError(err);
        })
      );

  }

  create(todo: TvWatchingSession): Observable<TvWatchingSession> {
    return this.http.post<TvWatchingSession>(this.url, todo)
      .pipe(
        catchError((err: any) => {
          console.error("Service.index() error: " + err);
          return throwError(err);
        })
      );

  }

  update(id: number, todo: TvWatchingSession): Observable<TvWatchingSession> {
    return this.http.put<TvWatchingSession>(this.url + '/' + id, todo).pipe(
      catchError((err: any) => {
        console.error("Service.index() error: " + err);
        return throwError(err);
      })
    );

  }

  destroy(id: Number) {
    return this.http.delete(this.url + '/' + id)
      .pipe(
        catchError((err: any) => {
          console.error("Service.index() error: " + err);
          return throwError(err);
        })
      );

  }

}

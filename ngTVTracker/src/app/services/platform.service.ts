import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Platform } from '../models/platform';

@Injectable({
  providedIn: 'root'
})
export class PlatformService {
  private baseUrl = 'http://localhost:8084/';
  private url = this.baseUrl + "api/platforms";

  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Platform[]> {
    return this.http.get<Platform[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.error("Service.index() error: " + err);
        return throwError(err);
      })
    );
  }
  show(sessionId): Observable<Platform> {
    return this.http.get<Platform>(this.url + '/' + sessionId)
      .pipe(
        catchError((err: any) => {
          console.error("Service.index() error: " + err);
          return throwError(err);
        })
      );

  }

  create(todo: Platform): Observable<Platform> {
    return this.http.post<Platform>(this.url, todo)
      .pipe(
        catchError((err: any) => {
          console.error("Service.index() error: " + err);
          return throwError(err);
        })
      );

  }

  update(id: number, todo: Platform): Observable<Platform> {
    return this.http.put<Platform>(this.url + '/' + id, todo).pipe(
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

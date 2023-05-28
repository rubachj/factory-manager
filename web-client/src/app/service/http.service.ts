import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { serverUrl } from '../core/constants/Endpoints';
import { token } from '../core/constants/LocalStorageName';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  constructor(private httpClient: HttpClient) {}

  public get<T>(url: string): Observable<T> {
    return this.httpClient.get<T>(serverUrl + url, {
      headers: this.createAuthHeaders(),
    });
  }

  public post<T>(url: string, body: any): Observable<T> {
    return this.httpClient.post<T>(serverUrl + url, body, {
      headers: this.createAuthHeaders(),
    });
  }

  public createAuthHeaders(): HttpHeaders {
    const savedToken = localStorage.getItem(token);
    if (savedToken) {
      return new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + savedToken,
      });
    }
    return null;
  }
}

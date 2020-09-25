import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authApiBaseUrl = 'http://localhost:8080/auth/';
  isAuthenticatedSubject: Subject<boolean>;
  isAuthenticated$: Observable<boolean>;

  constructor() {
    this.isAuthenticatedSubject = new Subject<boolean>();
    this.updateIsAuthStatus().then();
    this.isAuthenticated$ = this.isAuthenticatedSubject.asObservable();
  }

  async updateIsAuthStatus(): Promise<void> {
    await fetch(this.authApiBaseUrl + 'authenticated', {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
        Accept: '*/*'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
    }).then(r => {
      r.json().then(res => {
        this.isAuthenticatedSubject.next(res);
      });
    });
  }

  async signInWithEmailAndPassword(email: string, password: string): Promise<void> {
    const formData = new FormData();
    formData.append('email', email);
    formData.append('password', password);
    await fetch(this.authApiBaseUrl + 'login', {
      method: 'POST',
      credentials: 'include',
      // credentials: 'same-origin',
      headers: {
        Accept: '*/*'
      },
      body: formData
    });
    await this.updateIsAuthStatus();
  }

  async signUpWithUsernameEmailAndPassword(username: string, email: string, password: string): Promise<void> {
    const formData = new FormData();
    formData.append('username', username);
    formData.append('email', email);
    formData.append('password', password);
    await fetch(this.authApiBaseUrl + 'register', {
      method: 'POST',
      credentials: 'include',
      headers: {
        Accept: '*/*'
      },
      body: formData
    });
    await this.updateIsAuthStatus();
  }

  async logOutUser(): Promise<void> {
    await fetch(this.authApiBaseUrl + 'logout', {
      method: 'GET',
      credentials: 'include',
      headers: {
        Accept: '*/*'
      },
    });
  }
}

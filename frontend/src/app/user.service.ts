import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userApiBaseUrl = 'http://localhost:8080/api/user/';
  private user;

  constructor() {
    this.reloadUser().then();
  }

  async reloadUser(): Promise<void> {
    (await fetch(this.userApiBaseUrl, {
      method: 'GET',
      credentials: 'include',
      headers: {
        Accept: '*/*'
      },
    })).json().then(value => this.user = value);
  }

  getUser(): object {
    return this.user;
  }

  getUserId(): number {
    return this.user[`id`];
  }
}

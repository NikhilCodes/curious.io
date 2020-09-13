import axios from 'axios';
import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string;
  password: string;
  isLoggedIn: boolean;
  signInService: (email, password) => void;

  constructor(auth: AuthService, private router: Router) {
    auth.isAuthenticated$.subscribe((isAuth: boolean) => {
      this.isLoggedIn = isAuth;
      if (this.isLoggedIn !== false) {
        this.router.navigate(['/']).then();
      }
    });

    this.signInService = async (email, password) => {
      await auth.signInWithEmailAndPassword(email, password);
    };
  }

  onSignIn(): void {
    this.signInService(this.email, this.password);
  }
}

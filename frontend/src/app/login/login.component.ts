import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string;
  email: string;
  password: string;
  isLoggedIn: boolean;
  authMode = 1; // 1 - Login, -1 - Sign Up
  signInService: (email, password) => void;
  signUpService: (username, email, password) => void;

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

    this.signUpService = async (username, email, password) => {
      await auth.signUpWithUsernameEmailAndPassword(username, email, password);
    };
  }

  toggleAuthMode = () => this.authMode = -this.authMode;

  onSignIn(): void {
    this.signInService(this.email, this.password);
  }

  onSignUp(): void {
    this.signUpService(this.username, this.email, this.password);
  }
}

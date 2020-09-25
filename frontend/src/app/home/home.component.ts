import {Component, OnInit} from '@angular/core';
import {QNAService} from '../qna.service';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  homeQNAsData: object = null;

  constructor(service: QNAService, auth: AuthService, private router: Router) {
    auth.isAuthenticated$.subscribe(value => {
      if (value === false) {
        this.router.navigate(['/login']).then();
      }
    });
    service.getQNAs().then(data => {
      if ((data as Map<string, any>)[`status`] === 403) {
        auth.logOutUser().then();
      }
      this.homeQNAsData = data;
    });
  }

  async onClickAskQuestion(): Promise<void> {
    await this.router.navigate(['/ask']);
  }
}

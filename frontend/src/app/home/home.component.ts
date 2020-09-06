import {Component, OnInit} from '@angular/core';
import {QNAService} from '../qna.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  homeQNAsData: object = [];

  constructor(service: QNAService) {
    service.getQNAs().then(data => {
      this.homeQNAsData = data;
    });
  }
}

import {Component, OnInit} from '@angular/core';
import {QNAService} from '../qna.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-qnapage',
  templateUrl: './qnapage.component.html',
  styleUrls: ['./qnapage.component.css']
})
export class QNAPageComponent {
  qnaObj: object;

  constructor(service: QNAService, private route: ActivatedRoute) {
    service.getQNAById(parseInt(this.route.snapshot.paramMap.get('id'), 10)).then(data => {
      this.qnaObj = data;
    });

  }
}

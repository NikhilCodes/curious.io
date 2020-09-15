import {Component, OnInit} from '@angular/core';
import {QNAService} from '../qna.service';

@Component({
  selector: 'app-new-qnaform',
  templateUrl: './new-qnaform.component.html',
  styleUrls: ['./new-qnaform.component.css']
})
export class NewQNAFormComponent {
  title: string;
  body: string;

  addQuestionFunc: (title: string, body: string) => Promise<void>;

  constructor(service: QNAService) {
    this.addQuestionFunc = async (title, body) => service.addQuestion(title, body);
  }

  onClickPostQuestion(): void {
    if (!this.title || !this.body) {
      return;
    }
    this.addQuestionFunc(this.title, this.body).then(_ => window.location.href = '/');
  }
}

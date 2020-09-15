import {Component, Input, OnInit} from '@angular/core';
import {QNAService} from '../../qna.service';

@Component({
  selector: 'app-create-answer',
  templateUrl: './create-answer.component.html',
  styleUrls: ['./create-answer.component.css']
})
export class CreateAnswerComponent {
  editorTextConverted = '';
  newAnswerButtonDisabled = false;

  @Input() questionId: number;

  addAnswerToQuestionById: (id, answer) => void;

  constructor(service: QNAService) {
    // Services
    this.addAnswerToQuestionById = (id, answer) => {
      this.newAnswerButtonDisabled = true;
      console.log(id, answer);
      service.addAnswerToQuestionById(id, answer).then(r => window.location.reload());
    };
    //
  }
}

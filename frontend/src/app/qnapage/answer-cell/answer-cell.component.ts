import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-answer-cell',
  templateUrl: './answer-cell.component.html',
  styleUrls: ['./answer-cell.component.css']
})
export class AnswerCellComponent {
  @Input() answerObj: object;
}

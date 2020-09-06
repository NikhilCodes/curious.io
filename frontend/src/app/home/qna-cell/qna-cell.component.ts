import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-qna-cell',
  templateUrl: './qna-cell.component.html',
  styleUrls: ['./qna-cell.component.css']
})
export class QnaCellComponent {
  @Input() qnaObject: any;
}

import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-qna-cell',
  templateUrl: './qna-cell.component.html',
  styleUrls: ['./qna-cell.component.css']
})
export class QnaCellComponent implements OnInit {
  @Input() qnaObject: any;
  subTitle: string;

  ngOnInit(): void {
    this.subTitle = this.qnaObject.body.length > 150 ? this.qnaObject.body.substring(0, 135) + ' ...' : this.qnaObject.body.replace('<br>', ' ');
    this.subTitle = this.subTitle.replace(/<br>/g, ' ');
  }
}


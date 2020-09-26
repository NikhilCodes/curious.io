import {Component, Input, OnInit} from '@angular/core';
import {QNAService} from '../../qna.service';
import {UserService} from '../../user.service';

@Component({
  selector: 'app-answer-cell',
  templateUrl: './answer-cell.component.html',
  styleUrls: ['./answer-cell.component.css']
})
export class AnswerCellComponent implements OnInit {
  @Input() answerObj: object;
  @Input() qnaObj: object;

  voteAnswerFunc: (qId: number, aId: number, action: string) => Promise<number[]>;
  onUpVote: () => void;
  onDownVote: () => void;

  userId: number;

  upVotes: number[];
  downVotes: number[];

  voteCastType: number;
  // 0  = no vote cast
  // 1  = Upvote
  // -1 = DownVote

  constructor(qnaService: QNAService, userService: UserService) {
    this.userId = userService.getUserId();
    this.voteAnswerFunc = (qId: number, aId: number, action: string) => qnaService.voteAnswer(qId, aId, action);
  }

  ngOnInit(): void {
    this.upVotes = this.answerObj[`upVotes`];
    this.downVotes = this.answerObj[`downVotes`];
    this.voteCastType = this.upVotes.includes(this.userId) ? 1 : this.downVotes.includes(this.userId) ? -1 : 0;

    this.onUpVote = () => {
      this.voteAnswerFunc(this.qnaObj[`id`], this.answerObj[`id`], 'upvote').then(r => {
        const index = this.downVotes.indexOf(this.userId);
        if (index > -1) {
          this.downVotes.splice(index, 1);
        }
        this.upVotes = r;
      });
      this.voteCastType = this.voteCastType === 1 ? 0 : 1;
    };

    this.onDownVote = () => {
      this.voteAnswerFunc(this.qnaObj[`id`], this.answerObj[`id`], 'downvote').then(r => {
        const index = this.upVotes.indexOf(this.userId);
        if (index > -1) {
          this.upVotes.splice(index, 1);
        }
        this.downVotes = r;
      });
      this.voteCastType = this.voteCastType === -1 ? 0 : -1;
    };
  }
}

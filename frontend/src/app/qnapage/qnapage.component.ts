import {Component} from '@angular/core';
import {QNAService} from '../qna.service';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../user.service';

@Component({
  selector: 'app-qnapage',
  templateUrl: './qnapage.component.html',
  styleUrls: ['./qnapage.component.css']
})
export class QNAPageComponent {
  qnaObj: object;
  hasCastedVote: boolean;
  toggleUpvote: () => void;

  constructor(qnaService: QNAService, userService: UserService, private route: ActivatedRoute) {
    qnaService.getQNAById(parseInt(this.route.snapshot.paramMap.get('id'), 10)).then(data => {
      this.qnaObj = data;
      this.hasCastedVote = (this.qnaObj[`votes`] as number[]).includes(userService.getUserId());
    });

    this.toggleUpvote = () => {
      qnaService.toggleUpvote(this.qnaObj[`id`]).then(res => {
        this.qnaObj[`votes`] = res;
        this.hasCastedVote = (res as number[]).includes(userService.getUserId());
      });
    };
  }

  navigateToAskPage(): void {
    window.location.href = '/ask';
  }
}

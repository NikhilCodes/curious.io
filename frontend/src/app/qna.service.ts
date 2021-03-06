import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QNAService {
  qnaApiBaseUrl = 'http://localhost:8080/api/qna/';

  async getQNAs(): Promise<object> {
    return await ((await fetch(this.qnaApiBaseUrl, {
      method: 'GET',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
    })).json());
  }

  async getQNAById(id: number): Promise<object> {
    return await ((await fetch(this.qnaApiBaseUrl + `${id}`, {
      method: 'GET',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
    })).json());
  }

  async addQuestion(title, body): Promise<void> {
    await fetch(this.qnaApiBaseUrl, {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify({
        question: title,
        body,
      })
    }).then(_ => window.location.href = '/');
  }

  async addAnswerToQuestionById(id: number, answer: string): Promise<void> {
    await fetch(this.qnaApiBaseUrl + `${id}`, {
      method: 'PUT',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify({
        answer: `${answer}`
      })
    }).catch(reason => console.log(reason));
  }

  async toggleUpvote(id: number): Promise<number[]> {
    return await (await fetch(this.qnaApiBaseUrl + `${id}/vote`, {
      method: 'PUT',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'
      }
    })).json();
  }

  async voteAnswer(qId: number, aId: number, action: string): Promise<number[]> {
    return await (await fetch(this.qnaApiBaseUrl + `${qId}/${aId}/${action}`, {
      method: 'PUT',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'
      }
    })).json();
  }
}

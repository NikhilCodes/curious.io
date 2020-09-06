import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QNAService {
  qnaApiBaseUrl = 'http://localhost:8080/api/qna/';

  async getQNAs(): Promise<object> {
    return await ((await fetch(this.qnaApiBaseUrl, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
    })).json());
  }

  async getQNAById(id: number): Promise<object> {
    return await ((await fetch(this.qnaApiBaseUrl + `${id}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
    })).json());
  }
}

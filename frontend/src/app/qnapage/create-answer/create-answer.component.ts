import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-create-answer',
  templateUrl: './create-answer.component.html',
  styleUrls: ['./create-answer.component.css']
})
export class CreateAnswerComponent implements OnInit{
  editorText = '';
  editorHTMLObject: HTMLElement;

  ngOnInit(): void {
    this.editorHTMLObject = document.getElementById('answer-body-editor');
  }

  createBoldText(): void {
    this.editorText += '**BOLD**';
    // this.editorHTMLObject.focus();
    // console.log(this.editorHTMLObject.inputMode);
  }

  createItalicText(): void {
    this.editorText += '*Italic*';
    // TODO
  }
}

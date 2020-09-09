import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-create-answer',
  templateUrl: './create-answer.component.html',
  styleUrls: ['./create-answer.component.css']
})
export class CreateAnswerComponent implements OnInit {
  editorTextMd = '';
  editorTextConverted = '';
  editorHTMLObject: HTMLElement;

  ngOnInit(): void {
    this.editorHTMLObject = document.getElementById('answer-body-editor');
  }

  convertMdToHtml(): void {
    this.editorTextConverted = this.editorTextMd;
    let tagCount = 0;
    this.editorTextConverted = this.editorTextConverted.replace(/\n/g, '<br>');
    while (this.editorTextConverted.includes('__')) {
      if (tagCount % 2 === 0) {
        this.editorTextConverted = this.editorTextConverted.replace('__', '<strong>');
      } else {
        this.editorTextConverted = this.editorTextConverted.replace('__', '</strong>');
      }
      tagCount += 1;
    }

    while (this.editorTextConverted.includes('_')) {
      if (tagCount % 2 === 0) {
        this.editorTextConverted = this.editorTextConverted.replace('_', '<i>');
      } else {
        this.editorTextConverted = this.editorTextConverted.replace('_', '</i>');
      }
      tagCount += 1;
    }

    // Parsing <hr>, <h*>
    const lines = this.editorTextConverted.split('<br>');
    for (let i = 0; i < lines.length; i++) {
      if (lines[i].trim().length === 1) {
        lines[i] = lines[i].replace('-', '<hr color="black">');
      } else if (lines[i].startsWith('# ')) {
        lines[i] = lines[i].replace('# ', '<h2>');
        lines[i] += '</h2><hr color="grey">';
      } else if (lines[i].startsWith('## ')) {
        lines[i] = lines[i].replace('## ', '<h3>');
        lines[i] += '</h3><hr color="grey">';
      } else if (lines[i].startsWith('### ')) {
        lines[i] = lines[i].replace('### ', '<h4>');
        lines[i] += '</h4><hr color="grey">';
      }
    }

    this.editorTextConverted = lines.join('<br>');
  }

  createBoldText(): void {
    this.editorTextMd += '__BOLD__';
    this.convertMdToHtml();
    this.editorHTMLObject.focus();
    // TODO
    // console.log(this.editorHTMLObject.inputMode);
  }

  createItalicText(): void {
    this.editorTextMd += '_Italic_';
    this.convertMdToHtml();
    this.editorHTMLObject.focus();
    // TODO
  }
}

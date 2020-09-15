import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-pseudo-md-editor',
  templateUrl: './pseudo-md-editor.component.html',
  styleUrls: ['./pseudo-md-editor.component.css']
})
export class PseudoMdEditorComponent implements OnInit {
  editorTextMd = '';
  @Input() editorText = '';
  @Output() editorTextChange = new EventEmitter<string>();
  editorHTMLObject: HTMLElement;

  ngOnInit(): void {
    this.editorHTMLObject = document.getElementById('body-editor');
  }

  convertMdToHtml(): void {
    // Pseudo-Md --> Html
    this.editorText = this.editorTextMd;
    let tagCount = 0;
    this.editorText = this.editorText.replace(/\n/g, '<br>');
    while (this.editorText.includes('__')) {
      if (tagCount % 2 === 0) {
        this.editorText = this.editorText.replace('__', '<strong>');
      } else {
        this.editorText = this.editorText.replace('__', '</strong>');
      }
      tagCount += 1;
    }

    while (this.editorText.includes('_')) {
      if (tagCount % 2 === 0) {
        this.editorText = this.editorText.replace('_', '<i>');
      } else {
        this.editorText = this.editorText.replace('_', '</i>');
      }
      tagCount += 1;
    }

    // Parsing <hr>, <h*>
    const lines = this.editorText.split('<br>');
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

    this.editorText = lines.join('<br>');
    //
    this.editorTextChange.emit(this.editorText);
  }

  createBoldText(): void {
    this.editorTextMd += '__BOLD__';
    this.convertMdToHtml();
    this.editorTextChange.emit(this.editorText);
    this.editorHTMLObject.focus();
    // TODO
    // console.log(this.editorHTMLObject.inputMode);
  }

  createItalicText(): void {
    this.editorTextMd += '_Italic_';
    this.convertMdToHtml();
    this.editorTextChange.emit(this.editorText);
    this.editorHTMLObject.focus();
    // TODO
  }
}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { TopbarComponent } from './topbar/topbar.component';
import { HomeComponent } from './home/home.component';
import { QnaCellComponent } from './home/qna-cell/qna-cell.component';
import { QNAPageComponent } from './qnapage/qnapage.component';
import { AnswerCellComponent } from './qnapage/answer-cell/answer-cell.component';
import { FooterComponent } from './footer/footer.component';
import { CreateAnswerComponent } from './qnapage/create-answer/create-answer.component';
import {FormsModule} from '@angular/forms';
import {LoginComponent} from './login/login.component';
import { NewQNAFormComponent } from './new-qnaform/new-qnaform.component';
import { PseudoMdEditorComponent } from './widgets/pseudo-md-editor/pseudo-md-editor.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TopbarComponent,
    HomeComponent,
    QnaCellComponent,
    QNAPageComponent,
    AnswerCellComponent,
    FooterComponent,
    CreateAnswerComponent,
    NewQNAFormComponent,
    PseudoMdEditorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

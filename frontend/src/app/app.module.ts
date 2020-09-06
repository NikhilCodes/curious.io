import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { TopbarComponent } from './topbar/topbar.component';
import { HomeComponent } from './home/home.component';
import { QnaCellComponent } from './home/qna-cell/qna-cell.component';
import { QNAPageComponent } from './qnapage/qnapage.component';

@NgModule({
  declarations: [
    AppComponent,
    TopbarComponent,
    HomeComponent,
    QnaCellComponent,
    QNAPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

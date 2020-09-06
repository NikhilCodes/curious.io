import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {QNAPageComponent} from './qnapage/qnapage.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'question/:id', component: QNAPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

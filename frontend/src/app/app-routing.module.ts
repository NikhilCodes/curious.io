import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {QNAPageComponent} from './qnapage/qnapage.component';
import {LoginComponent} from './login/login.component';
import {NewQNAFormComponent} from './new-qnaform/new-qnaform.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'ask', component: NewQNAFormComponent},
  {path: 'question/:id', component: QNAPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

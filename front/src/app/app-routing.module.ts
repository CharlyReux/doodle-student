import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponentComponent } from './home-component/home-component.component';
import { CreatePollComponentComponent } from './create-poll-component/create-poll-component.component';
import { AnswerPollComponent } from './answer-poll/answer-poll.component';
import { AdminPollComponent } from './admin-poll/admin-poll.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponentComponent
  },
  {
    path: 'create',
    component: CreatePollComponentComponent
  },
  {
    path: 'update/:slugadminid',
    component: CreatePollComponentComponent
  },
  {
    path: 'answer/:slugid',
    component: AnswerPollComponent
  },
  {
    path: 'admin/:slugadminid',
    component: AdminPollComponent
  },
  {
    path: 'board',
    component: DashboardComponent
  },
  {
    path: 'Reg',
    component: RegisterComponent
  },
  {
    path: 'log',
    component: LoginComponent
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

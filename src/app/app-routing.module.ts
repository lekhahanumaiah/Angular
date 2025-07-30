

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
// import { HomeComponent } from './home.component';
// Update the import path below if the file exists elsewhere, for example:
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: 'app', component: AppComponent},
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent},
  {path:'**', component:LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


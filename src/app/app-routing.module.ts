

// import { NgModule } from '@angular/core';
// import { RouterModule, Routes } from '@angular/router';
// import { AppComponent } from './app.component';
// import { LoginComponent } from './login/login.component';
// // import { HomeComponent } from './home.component';
// // Update the import path below if the file exists elsewhere, for example:
// import { HomeComponent } from './home/home.component';
// import { CardComponent } from './card/card.component';

// const routes: Routes = [
//   { path: 'app', component: AppComponent},
//   { path: 'login', component: LoginComponent },
//   { path: 'home', component: HomeComponent},
//   { path: 'products', component: CardComponent }

// ];

// @NgModule({
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule]
// })
// export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
// import { CardComponent } from './card/card.component';

const routes: Routes = [
  { path: '', component: HomeComponent },       // default route
  { path: 'login', component: LoginComponent }, // login page
  { path: 'home', component: HomeComponent },   // optional alias for home
  // { path: 'products', component: CardComponent },
  { path: '**', redirectTo: '' }                // wildcard → go back to home
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

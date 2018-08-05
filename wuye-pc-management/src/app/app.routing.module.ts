// angular core
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

// all components
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';

const _routes: Routes = [{
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  }, {
    path: 'login',
    component: LoginComponent
  }, {
    path: 'home',
    component: HomeComponent
  }];

@NgModule({
  imports: [RouterModule.forRoot(_routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}

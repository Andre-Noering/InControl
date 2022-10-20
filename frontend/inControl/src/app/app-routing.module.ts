
import { CadastroScreenComponent } from './cadastro-screen/cadastro-screen.component';
import { Injectable, NgModule } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, RouterModule } from '@angular/router';
import { AuthenticationService } from './helpers/auth.service';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginScreenComponent } from './login-screen/login-screen.component';
import { LojasScreenComponent } from './lojas/lojas-screen/lojas-screen.component';
import { SobreNosScreenComponent } from './sobre-nos-screen/sobre-nos-screen.component';

const routes: Routes = [
  {path: '', component: LandingPageComponent},
  {path: 'login', component: LoginScreenComponent},
  {path: 'cadastro', component: CadastroScreenComponent},
  {path: 'lojas', component: LojasScreenComponent},
  {path: 'informacoes', component: SobreNosScreenComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


import { CadastroScreenComponent } from './cadastro-screen/cadastro-screen.component';
import { Injectable, NgModule } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, RouterModule } from '@angular/router';
import { AuthenticationService } from './helpers/auth.service';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginScreenComponent } from './login-screen/login-screen.component';
import { LojasScreenComponent } from './lojas/lojas-screen/lojas-screen.component';
import { AddLojaComponent } from './lojas/add-loja/add-loja.component';
import { ItemScreenComponent } from './estoque/item-screen/item-screen.component';
import { AddItemComponent } from './estoque/add-item/add-item.component';
import { VendaListItemComponent } from './vendas/venda-list-item/venda-list-item.component';
import { VendaScreenComponent } from './vendas/venda-screen/venda-screen.component';
import { SobreNosScreenComponent } from './sobre-nos-screen/sobre-nos-screen.component';
import { ItemLojaComponent } from './lojas/item-loja/item-loja.component';

const routes: Routes = [
  {path: 'venda', component: ItemScreenComponent},
  {path: '', component: LandingPageComponent},
  {path: 'login', component: LoginScreenComponent},
  {path: 'cadastro', component: CadastroScreenComponent},
  {path: 'lojas', component: LojasScreenComponent},
  {path: 'lojas/:razao_social', component: ItemLojaComponent},
  {path: 'lojas/:razao_social/estoque', component: ItemScreenComponent},
  {path: 'lojas/:razao_social/estoque/adicionar', component: AddItemComponent},
  {path: `estoque/adicionar`, component: AddItemComponent},
  {path: 'adicionarLoja', component: AddLojaComponent},
  {path: 'SobreNos', component: SobreNosScreenComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

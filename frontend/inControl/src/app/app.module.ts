import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginScreenComponent } from './login-screen/login-screen.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { CadastroScreenComponent } from './cadastro-screen/cadastro-screen.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LojasScreenComponent } from './lojas/lojas-screen/lojas-screen.component';
import { LojasListItemComponent } from './lojas/lojas-list-item/lojas-list-item.component';
import { AddLojaComponent } from './lojas/add-loja/add-loja.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { BasicAuthInterceptor } from './helpers/basic-auth.interceptor';
import { LojaItemComponent } from './lojas/loja-item/loja-item.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { BasicAuthInterceptor } from './helpers/basic-auth.interceptor';
import { ItemListItemComponent } from './estoque/item-list-item/item-list-item.component';
import { ItemScreenComponent } from './estoque/item-screen/item-screen.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginScreenComponent,
    LandingPageComponent,
    CadastroScreenComponent,
    LojasScreenComponent,
    LojasListItemComponent,
    AddLojaComponent,
    LojaItemComponent
    ItemListItemComponent,
    ItemScreenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    {provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
export type User = {
  id: number;
  login: string;
  password: string;
  authdata?: string;
}
export type Funcionario = {
  idPessoa: number;
  nome: string;
  sobrenome: string;
  cpf: string;
  telefone:string;
  login: string;
  senha:string;
}

export type Loja = {
  razao_social: string;
  cnpj:string;
  contato:string;
  valor_caixa:number;
  id_funcionario:number;
}

export type Item = {
  nome: string;
  valor: number,
  qtdeEstoque: number,
  qtdeAlertaEstoque: number
}
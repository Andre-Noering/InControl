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
import { ItemListItemComponent } from './estoque/item-list-item/item-list-item.component';
import { ItemScreenComponent } from './estoque/item-screen/item-screen.component';
import { AddItemComponent } from './estoque/add-item/add-item.component';
import { FuncionarioScreenComponent } from './funcionarios/funcionario-screen/funcionario-screen.component';
import { FuncionarioListItemComponent } from './funcionarios/funcionario-list-item/funcionario-list-item.component';
import { AddFuncionarioComponent } from './funcionarios/add-funcionario/add-funcionario.component';
import { ItemLojaComponent } from './lojas/item-loja/item-loja.component';
import { SobreNosScreenComponent } from './sobre-nos-screen/sobre-nos-screen.component';
import { VendaListItemComponent } from './vendas/venda-list-item/venda-list-item.component';
import { VendaScreenComponent } from './vendas/venda-screen/venda-screen.component';
import { AddVendaComponent } from './vendas/add-venda/add-venda.component';
import { FornecedorScreenComponent } from './fornecedores/fornecedor-screen/fornecedor-screen.component';
import { FornecedorListItemComponent } from './fornecedores/fornecedor-list-item/fornecedor-list-item.component';
import { AddFornecedorComponent } from './fornecedores/add-fornecedor/add-fornecedor.component';
import { ItemVendaScreenComponent } from './venda/itensvenda/item-venda-screen/item-venda-screen.component';
import { ItemVendaListItemComponent } from './venda/itensvenda/item-venda-list-item/item-venda-list-item.component';
import { AddItemVendaComponent } from './venda/itensvenda/add-item-venda/add-item-venda.component';
import { PedidosCompraScreenComponent } from './pedidos-compra/pedidos-compra-screen/pedidos-compra-screen.component';
import { PessoasScreenComponent } from './pessoas/pessoas-screen/pessoas-screen.component';
import { PessoasListItemComponent } from './pessoas/pessoas-list-item/pessoas-list-item.component';
import { PedidoCompraListItemComponent } from './pedidos-compra/pedido-compra-list-item/pedido-compra-list-item.component';
import { AddPessoaComponent } from './pessoas/add-pessoa/add-pessoa.component';
import { ItemFornecedorScreenComponent } from './item-fornecedor/item-fornecedor-screen/item-fornecedor-screen.component';
import { ItemFornecedorListITemComponent } from './item-fornecedor/item-fornecedor-list-item/item-fornecedor-list-item.component';
import { AddItemFornecedorComponent } from './item-fornecedor/add-item-fornecedor/add-item-fornecedor.component';
import { PedidoCompraItemFornecedorScreenComponent } from './pedido-compra-item-fornecedor/pedido-compra-item-fornecedor-screen/pedido-compra-item-fornecedor-screen.component';
import { AddPedidoCompraComponent } from './pedidos-compra/add-pedido-compra/add-pedido-compra.component';
import { PedidoCompraItemFornecedorListItemComponent } from './pedido-compra-item-fornecedor/pedido-compra-item-fornecedor-list-item/pedido-compra-item-fornecedor-list-item.component';
import { AddPedidoCompraItemFornecedorComponent } from './pedido-compra-item-fornecedor/add-pedido-compra-item-fornecedor/add-pedido-compra-item-fornecedor.component';
import { PedidosCompraFuncionarioScreenComponent } from './pedidos-compra/pedidos-compra-funcionario-screen/pedidos-compra-funcionario-screen.component';
import { VendasFuncionarioScreenComponent } from './vendas/vendas-funcionario-screen/vendas-funcionario-screen.component';
import { ContaScreenComponent } from './conta-screen/conta-screen.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginScreenComponent,
    LandingPageComponent,
    CadastroScreenComponent,
    LojasScreenComponent,
    LojasListItemComponent,
    AddLojaComponent,
    ItemListItemComponent,
    ItemScreenComponent,
    AddItemComponent,
    FuncionarioScreenComponent,
    FuncionarioListItemComponent,
    AddFuncionarioComponent,
    ItemLojaComponent,
    FuncionarioScreenComponent,
    SobreNosScreenComponent,
    VendaListItemComponent,
    VendaScreenComponent,
    AddVendaComponent,
    FornecedorScreenComponent,
    FornecedorListItemComponent,
    AddFornecedorComponent,
    ItemVendaScreenComponent,
    ItemVendaListItemComponent,
    AddItemVendaComponent,
    PedidosCompraScreenComponent,
    PessoasScreenComponent,
    PessoasListItemComponent,
    PedidoCompraListItemComponent,
    AddPessoaComponent,
    ItemFornecedorScreenComponent,
    ItemFornecedorListITemComponent,
    AddItemFornecedorComponent,
    PedidoCompraItemFornecedorScreenComponent,
    AddPedidoCompraComponent,
    PedidoCompraItemFornecedorListItemComponent,
    AddPedidoCompraItemFornecedorComponent,
    PedidosCompraFuncionarioScreenComponent,
    VendasFuncionarioScreenComponent,
    ContaScreenComponent
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
  idPessoa: number;
  login: string;
  password: string;
  authdata?: string;
}
export type Gerente = {
  idPessoa: number;
  nome: string;
  sobrenome: string;
  cpf: string;
  ativo:boolean;
  telefone:string;
  login: string;
  senha:string;
}
export type Funcionario = {
  idPessoa: number;
  nome: string;
  sobrenome: string;
  cpf: string;
  ativo:boolean;
  telefone:string;
  login: string;
  senha:string;
  idLoja:number;
}

export type Pessoa = {
  idPessoa: number;
  nome: string;
  sobrenome: string;
  telefone: string;
  ativo:boolean;
  cpf: string;
  idLoja: number;
}

export type Loja = {
  id:number,
  razao_social: string;
  cnpj:string;
  contato:string;
  valor_caixa:number;
  id_funcionario:number;
}

export type Item = {
  id:number;
  nome: string;
  ativo:boolean;
  valor: number,
  qtdeEstoque: number,
  qtdeAlertaEstoque: number,
  idLoja:number
}

export type ItemFornecedor = {
  id:number|null;
  idFornecedor:number;
  idItem:number;
  ativo:boolean;
  valorCompra:number;
  nome_item:string|null;
  nome_fornecedor:string|null;
}


export type Venda = {
  id: number | null;
  data: Date | null;
  id_vendedor: number;
  id_cliente: number;
  nome_cliente: string;
  nome_vendedor: string;
  valor : number;
}
export type PedidoCompra = {
  id: number | null;
  data: Date | null;
  id_funcionario: number;
  nome_funcionario: string;
  valor : number;
}
export type PedidoCompraItemFornecedor = {
  id:number|null;
  valor_unitario:number|null;
  qtde:number|null;
  idFornecedor:number|null;
  idPedidoCompra:number|null;
  nomeItem:string|null;
  nomeFornecedor:string|null;
}

export type Fornecedor = {
  id:number,
  razao_social: string;
  cnpj:string;
  ativo:boolean;
  contato:string;
  idLoja:number;
}

export type ItemVenda = {
  id:number;
  idVenda:number;
  idItem:number;
  qtde:number;
  valorUnitario:number;
  nome:string;
  qtdeEstoque:number;
}
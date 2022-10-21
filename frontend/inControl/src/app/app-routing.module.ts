
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
import { FuncionarioScreenComponent } from './funcionarios/funcionario-screen/funcionario-screen.component';
import { AddFuncionarioComponent } from './funcionarios/add-funcionario/add-funcionario.component';
import { AddVendaComponent } from './vendas/add-venda/add-venda.component';
import { FornecedorScreenComponent } from './fornecedores/fornecedor-screen/fornecedor-screen.component';
import { AddFornecedorComponent } from './fornecedores/add-fornecedor/add-fornecedor.component';
import { ItemVendaScreenComponent } from './venda/itensvenda/item-venda-screen/item-venda-screen.component';
import { AddItemVendaComponent } from './venda/itensvenda/add-item-venda/add-item-venda.component';
import { PedidosCompraScreenComponent } from './pedidos-compra/pedidos-compra-screen/pedidos-compra-screen.component';
import { PessoasScreenComponent } from './pessoas/pessoas-screen/pessoas-screen.component';
import { AddPessoaComponent } from './pessoas/add-pessoa/add-pessoa.component';
import { ItemFornecedorScreenComponent } from './item-fornecedor/item-fornecedor-screen/item-fornecedor-screen.component';

const routes: Routes = [
  {path: 'venda', component: ItemScreenComponent},
  {path: '', component: LandingPageComponent},
  {path: 'login', component: LoginScreenComponent},
  {path: 'cadastro', component: CadastroScreenComponent},
  {path: 'lojas', component: LojasScreenComponent},
  {path: 'lojas/:razao_social', component: ItemLojaComponent},
  {path: 'lojas/:razao_social/funcionarios', component: FuncionarioScreenComponent},
  {path: 'lojas/:razao_social/pessoas', component: PessoasScreenComponent},
  {path: 'lojas/:razao_social/pessoas/adicionar', component: AddPessoaComponent},
  {path: 'lojas/:razao_social/fornecedores', component: FornecedorScreenComponent},
  {path: 'lojas/:razao_social/fornecedores', component: FornecedorScreenComponent},
  {path: 'lojas/:razao_social/itensFornecedor', component: ItemFornecedorScreenComponent},
  {path: 'lojas/:razao_social/pedidosCompra/adicionar', component: PedidosCompraScreenComponent},
  {path: 'lojas/:razao_social/fornecedores/adicionar', component: AddFornecedorComponent},
  {path: 'lojas/:razao_social/funcionarios/adicionar', component: AddFuncionarioComponent},
  {path: 'lojas/:razao_social/vendas', component: VendaScreenComponent},
  {path: 'lojas/:razao_social/vendas/adicionar', component: AddVendaComponent},
  {path: 'lojas/:razao_social/vendas/:id/itensVenda', component: ItemVendaScreenComponent},
  {path: 'lojas/:razao_social/vendas/:id/itensVenda/adicionar', component: AddItemVendaComponent},
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

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VendaListItemComponent } from './venda-list-item/venda-list-item.component';
import { VendaScreenComponent } from './venda-screen/venda-screen.component';
import { AddVendaComponent } from './add-venda/add-venda.component';
import { SelectFuncionarioComponent } from './select-funcionario/select-funcionario.component';
import { VendasFuncionarioScreenComponent } from './vendas-funcionario-screen/vendas-funcionario-screen.component';



@NgModule({
  declarations: [
    VendaListItemComponent,
    VendaScreenComponent,
    AddVendaComponent,
    SelectFuncionarioComponent,
    VendasFuncionarioScreenComponent
  ],
  imports: [
    CommonModule
  ]
})
export class VendasModule { }

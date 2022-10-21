import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VendaListItemComponent } from './venda-list-item/venda-list-item.component';
import { VendaScreenComponent } from './venda-screen/venda-screen.component';
import { AddVendaComponent } from './add-venda/add-venda.component';
import { SelectFuncionarioComponent } from './select-funcionario/select-funcionario.component';



@NgModule({
  declarations: [
    VendaListItemComponent,
    VendaScreenComponent,
    AddVendaComponent,
    SelectFuncionarioComponent
  ],
  imports: [
    CommonModule
  ]
})
export class VendasModule { }

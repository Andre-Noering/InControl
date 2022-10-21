import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VendaListItemComponent } from './venda-list-item/venda-list-item.component';
import { VendaScreenComponent } from './venda-screen/venda-screen.component';
<<<<<<< HEAD
=======
import { AddVendaComponent } from './add-venda/add-venda.component';
import { SelectFuncionarioComponent } from './select-funcionario/select-funcionario.component';
>>>>>>> bed69b0c76019916a6b311173dde8d437d29ee95



@NgModule({
  declarations: [
    VendaListItemComponent,
<<<<<<< HEAD
    VendaScreenComponent
=======
    VendaScreenComponent,
    AddVendaComponent,
    SelectFuncionarioComponent
>>>>>>> bed69b0c76019916a6b311173dde8d437d29ee95
  ],
  imports: [
    CommonModule
  ]
})
export class VendasModule { }

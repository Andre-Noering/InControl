import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VendaListItemComponent } from './venda-list-item/venda-list-item.component';
import { VendaScreenComponent } from './venda-screen/venda-screen.component';
import { AddVendaComponent } from './add-venda/add-venda.component';



@NgModule({
  declarations: [
    VendaListItemComponent,
    VendaScreenComponent,
    AddVendaComponent
  ],
  imports: [
    CommonModule
  ]
})
export class VendasModule { }

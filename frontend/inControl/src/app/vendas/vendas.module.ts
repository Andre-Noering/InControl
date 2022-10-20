import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VendaListItemComponent } from './venda-list-item/venda-list-item.component';
import { VendaScreenComponent } from './venda-screen/venda-screen.component';



@NgModule({
  declarations: [
    VendaListItemComponent,
    VendaScreenComponent
  ],
  imports: [
    CommonModule
  ]
})
export class VendasModule { }

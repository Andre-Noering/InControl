import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItensComponent } from './itens/itens.component';
import { ItemListItemComponent } from './item-list-item/item-list-item.component';
import { ItemScreenComponent } from './item-screen/item-screen.component';



@NgModule({
  declarations: [
    ItensComponent,
    ItemListItemComponent,
    ItemScreenComponent
  ],
  imports: [
    CommonModule
  ]
})
export class EstoqueModule { }

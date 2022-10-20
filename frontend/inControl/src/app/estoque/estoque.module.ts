import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItensComponent } from './itens/itens.component';
import { ItemListItemComponent } from './item-list-item/item-list-item.component';
import { ItemScreenComponent } from './item-screen/item-screen.component';
import { AddItemComponent } from './add-item/add-item.component';



@NgModule({
  declarations: [
    ItensComponent,
    ItemListItemComponent,
    ItemScreenComponent,
    AddItemComponent
  ],
  imports: [
    CommonModule
  ]
})
export class EstoqueModule { }

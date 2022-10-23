import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Item } from 'src/app/app.module';

@Component({
  selector: 'app-item-list-item',
  templateUrl: './item-list-item.component.html',
  styleUrls: ['./item-list-item.component.css']
})
export class ItemListItemComponent implements OnInit {

  @Input() item!:Item;
  @Input() escolhendo:boolean = false;
  @Output() selecionado = new EventEmitter<Item>();

  
  constructor() { }

  ngOnInit(): void {
  }
  selectItem(){
    this.selecionado.emit(this.item);
    this.escolhendo=false;
  }

  deleteItem(){
    this.selecionado.emit(this.item)
  }

}

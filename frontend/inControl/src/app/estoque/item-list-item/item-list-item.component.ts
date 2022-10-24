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
  @Output() delete = new EventEmitter<Item>();
  @Output() edit = new EventEmitter<Item>();
  @Output() selecionado = new EventEmitter<Item>();
  @Output() editar = new EventEmitter<boolean>();

  
  constructor() { }

  ngOnInit(): void {
  }
  selectItem(){
    this.selecionado.emit(this.item);
    this.escolhendo=false;
  }

  deleteItem(){
    this.delete.emit(this.item)
  }
  editItem(){
    this.edit.emit(this.item)
    this.editar.emit(true);
  }
}

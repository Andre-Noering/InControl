import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ItemFornecedor } from 'src/app/app.module';

@Component({
  selector: 'app-item-fornecedor-list-item',
  templateUrl: './item-fornecedor-list-item.component.html',
  styleUrls: ['./item-fornecedor-list-item.component.css']
})
export class ItemFornecedorListITemComponent implements OnInit {
  @Input() itemFornecedor: ItemFornecedor|null = null;
  @Input() escolhendo:boolean = false;
  @Output() delete = new EventEmitter<ItemFornecedor>();
  @Output() edit = new EventEmitter<ItemFornecedor>();
  @Output() selecionado = new EventEmitter<ItemFornecedor>();
  @Output() editar = new EventEmitter<boolean>();

  
  constructor() { }

  ngOnInit(): void {
  }
  selectItem(){
    this.selecionado.emit(this.itemFornecedor!);
    this.escolhendo=false;
  }
  deleteItem(){
    this.selecionado.emit(this.itemFornecedor!);
  }
  editPessoa(){
    this.edit.emit(this.itemFornecedor!)
    this.editar.emit(true);
  }
}

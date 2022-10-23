import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Fornecedor } from 'src/app/app.module';

@Component({
  selector: 'app-fornecedor-list-item',
  templateUrl: './fornecedor-list-item.component.html',
  styleUrls: ['./fornecedor-list-item.component.css']
})
export class FornecedorListItemComponent implements OnInit {
  @Input() fornecedor!: Fornecedor;
  @Input() escolhendo:boolean = false;
  @Output() selecionado = new EventEmitter<Fornecedor>();

  
  constructor() { }

  ngOnInit(): void {
  }
  selectFornecedor(){
    this.selecionado.emit(this.fornecedor);
    this.escolhendo=false;
  }

}

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Loja } from 'src/app/app.module';

@Component({
  selector: 'app-item-loja',
  templateUrl: './item-loja.component.html',
  styleUrls: ['./item-loja.component.css']
})
export class ItemLojaComponent implements OnInit {
  @Input() loja: Loja |null=null;
  @Output() lojaNull = new EventEmitter<null>();
  @Output() itens = new EventEmitter<boolean>();
  @Output() funcionarios = new EventEmitter<boolean>();
  
  constructor() { }

  ngOnInit(): void {
  }
  sair(){
    this.lojaNull.emit(null);
  }
  setItens(valor:boolean){
    this.itens.emit(valor);
  }
  setFuncionarios(valor:boolean){
    this.funcionarios.emit(valor);
  }
 
}

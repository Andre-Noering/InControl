import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Funcionario } from 'src/app/app.module';

@Component({
  selector: 'app-funcionario-list-item',
  templateUrl: './funcionario-list-item.component.html',
  styleUrls: ['./funcionario-list-item.component.css']
})
export class FuncionarioListItemComponent implements OnInit {
  @Input() funcionario!:Funcionario;
  @Input() escolhendo:boolean = false;
  @Output() selecionado = new EventEmitter<Funcionario>();
  constructor() { }

  ngOnInit(): void {
  }
  selectFunc(){
    this.selecionado.emit(this.funcionario);
    this.escolhendo=false;
  }
}

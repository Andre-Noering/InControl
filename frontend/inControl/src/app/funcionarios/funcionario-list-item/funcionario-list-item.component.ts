import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Funcionario } from 'src/app/app.module';
import { FuncionarioService } from 'src/app/services/funcionario.service';

@Component({
  selector: 'app-funcionario-list-item',
  templateUrl: './funcionario-list-item.component.html',
  styleUrls: ['./funcionario-list-item.component.css']
})
export class FuncionarioListItemComponent implements OnInit {
  @Input() funcionario!:Funcionario;
  @Input() escolhendo:boolean = false;
  @Output() delete = new EventEmitter<Funcionario>();
  @Output() edit = new EventEmitter<Funcionario>();
  @Output() selecionado = new EventEmitter<Funcionario>();
  @Output() editar = new EventEmitter<boolean>();
  constructor(private funcionarioService:FuncionarioService) { }

  ngOnInit(): void {
  }
  selectFunc(){
    this.selecionado.emit(this.funcionario);
    this.escolhendo=false;
  }
  deleteFunc(){
    this.delete.emit(this.funcionario);
  }
  editFunc(){
    this.edit.emit(this.funcionario)
    this.editar.emit(true);
  }
}

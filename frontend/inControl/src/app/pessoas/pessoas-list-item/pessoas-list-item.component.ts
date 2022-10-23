import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Pessoa } from 'src/app/app.module';

@Component({
  selector: 'app-pessoas-list-item',
  templateUrl: './pessoas-list-item.component.html',
  styleUrls: ['./pessoas-list-item.component.css']
})
export class PessoasListItemComponent implements OnInit {
  @Input() pessoa!:Pessoa;
  @Input() escolhendo:boolean = false;
  @Output() selecionado = new EventEmitter<Pessoa>();
  constructor() { }

  ngOnInit(): void {
  }
  selectPessoa(){
    this.selecionado.emit(this.pessoa);
    this.escolhendo=false;
  }
}

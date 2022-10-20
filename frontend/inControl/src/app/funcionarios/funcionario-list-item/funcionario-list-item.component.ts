import { Component, Input, OnInit } from '@angular/core';
import { Funcionario } from 'src/app/app.module';

@Component({
  selector: 'app-funcionario-list-item',
  templateUrl: './funcionario-list-item.component.html',
  styleUrls: ['./funcionario-list-item.component.css']
})
export class FuncionarioListItemComponent implements OnInit {
  @Input() funcionario!:Funcionario;
  constructor() { }

  ngOnInit(): void {
  }

}

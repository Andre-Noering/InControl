import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Funcionario, Loja } from 'src/app/app.module';
import { FuncionarioService } from 'src/app/services/funcionario.service';

@Component({
  selector: 'app-funcionario-screen',
  templateUrl: './funcionario-screen.component.html',
  styleUrls: ['./funcionario-screen.component.css']
})
export class FuncionarioScreenComponent implements OnInit {
  @Input() loja:Loja | null = null;
  @Output() adicionandoFuncionario = new EventEmitter<boolean>();
  
  funcionarios:Funcionario[] = [];
  constructor(
    private funcionarioService: FuncionarioService
  ) { }

  ngOnInit(): void {
    if(this.loja!=null){
    this.funcionarioService.getAll(this.loja!.razao_social).pipe().subscribe(funcionarios => {
      this.funcionarios = this.funcionarios;
    })
    console.log(this.funcionarios);
  };
}
}

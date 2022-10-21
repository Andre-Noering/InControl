import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Funcionario, Loja } from 'src/app/app.module';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-funcionario-screen',
  templateUrl: './funcionario-screen.component.html',
  styleUrls: ['./funcionario-screen.component.css']
})
export class FuncionarioScreenComponent implements OnInit {
  @Input() loja:Loja | null = null;
  
  funcionarios:Funcionario[] = [];
  constructor(
    private funcionarioService: FuncionarioService,
    private lojaService: LojaService,
    private route: ActivatedRoute
  ) {
    this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
      this.loja = resultado;
      this.funcionarioService.getAll(this.loja!.razao_social).pipe().subscribe(funcionarios => {
        this.funcionarios = funcionarios;
      });
    },
    erro => {
      if(erro.status == 400) {
        console.log(erro);
      }
    }));
   }

  ngOnInit(): void {
    if(this.loja!=null){
    this.funcionarioService.getAll(this.loja!.razao_social).pipe().subscribe(funcionarios => {
      this.funcionarios = this.funcionarios;
    })
  };
}
}

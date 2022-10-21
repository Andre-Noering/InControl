import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Loja, Pessoa } from 'src/app/app.module';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-pessoas-screen',
  templateUrl: './pessoas-screen.component.html',
  styleUrls: ['./pessoas-screen.component.css']
})
export class PessoasScreenComponent implements OnInit {
  loja:Loja|null=null;
  pessoas:Pessoa[] = [];
  constructor(
    private lojaService: LojaService,
    private route: ActivatedRoute,
    private pessoaService : PessoaService
  ) {
    this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
      this.loja = resultado;
      this.pessoaService.getClientes(this.loja!.razao_social).pipe().subscribe(clientes => {
        this.pessoas = clientes;
      });
    },
    erro => {
      if(erro.status == 400) {
        console.log(erro);
      }
    }));
   }

  ngOnInit(): void {
  }

}

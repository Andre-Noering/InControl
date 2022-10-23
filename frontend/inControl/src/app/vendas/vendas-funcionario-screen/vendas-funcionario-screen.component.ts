import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Funcionario, Loja, User, Venda } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-vendas-funcionario-screen',
  templateUrl: './vendas-funcionario-screen.component.html',
  styleUrls: ['./vendas-funcionario-screen.component.css']
})
export class VendasFuncionarioScreenComponent  {

  loja: Loja |null=null;
  funcionario: Funcionario|null = null;
  user: User | null = null;
  adicionando:boolean=false;
  vendas: Venda[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private vendaService:VendaService,
    private lojaService: LojaService,
    private funcionarioService: FuncionarioService,
    private route: ActivatedRoute) {
      this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;

        this.vendaService.getAllById(params['id']).pipe().subscribe(vendas => {
          this.vendas = vendas;
          this.funcionarioService.getById(params['id']).subscribe(func => {this.funcionario=func},
            erro => {
              if(erro.status == 400) {
                console.log(erro);
              }
            });
        },
        erro => {
          if(erro.status == 400) {
            console.log(erro);
          }
        });
      },
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      }));
   }
}

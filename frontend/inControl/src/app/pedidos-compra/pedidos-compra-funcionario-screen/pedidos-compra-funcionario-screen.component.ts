import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Funcionario, Loja, PedidoCompra, User } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';
import { PedidoCompraService } from 'src/app/services/pedido-compra.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-pedidos-compra-funcionario-screen',
  templateUrl: './pedidos-compra-funcionario-screen.component.html',
  styleUrls: ['./pedidos-compra-funcionario-screen.component.css']
})
export class PedidosCompraFuncionarioScreenComponent {
  adicionando:boolean=false;
  user: User | null = null;
  funcionario: Funcionario|null = null;
  loja: Loja |null=null;
  pedidos: PedidoCompra[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private pedidoCompraService:PedidoCompraService,
    private lojaService: LojaService,
    private funcionarioService: FuncionarioService,
    private route: ActivatedRoute) {
      this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;

        this.pedidoCompraService.getAllById(params['id']).pipe().subscribe(pedidos => {
          this.pedidos = pedidos;
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


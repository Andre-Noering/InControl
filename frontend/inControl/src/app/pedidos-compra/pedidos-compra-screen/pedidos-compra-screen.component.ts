import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Loja, PedidoCompra } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { LojaService } from 'src/app/services/loja.service';
import { PedidoCompraService } from 'src/app/services/pedido-compra.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-pedidos-compra-screen',
  templateUrl: './pedidos-compra-screen.component.html',
  styleUrls: ['./pedidos-compra-screen.component.css']
})
export class PedidosCompraScreenComponent{
  loja:Loja|null=null
  pedidos: PedidoCompra[] = []
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private pedidoCompraService:PedidoCompraService,
    private lojaService: LojaService,
    private route: ActivatedRoute) {
      this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;
        this.pedidoCompraService.getAll(resultado.razao_social).pipe().subscribe(pedidos => {
          this.pedidos = pedidos;
        });
      },
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      }));
   }
}

  
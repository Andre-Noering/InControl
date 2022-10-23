import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {  User, Venda, Pessoa, PedidoCompra } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class PedidoCompraService {

    user: User | null = null;
    constructor(
    private http: HttpClient,
    private router:Router,
    private authenticationService: AuthenticationService) { 
    this.authenticationService.user.subscribe(x => this.user = x);
    }

    getAll(razao_social:string) {
        return this.http.get<PedidoCompra[]>(`/loja/${razao_social}/pedidos`);
    }
    getAllById(id:number) {
      return this.http.get<PedidoCompra[]>(`/funcionarios/pedidos/${id}`);
  }
    
    getPedido(id:number) {
      return this.http.get<PedidoCompra>(`/pedido-compra/${id}`);
    }

    finalizar(id:number){
      this.http.post(`/pedido-compra/finalizar`, id).subscribe(
        sucesso=> {console.log(sucesso);},
       erro => {
        console.log(erro);
      })
    }

    add(pedido:PedidoCompra){
      return this.http.post<PedidoCompra>(`/pedido-compra/adicionar`, pedido);
    }
    
}
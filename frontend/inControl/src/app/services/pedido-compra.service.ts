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
        return this.http.get<PedidoCompra[]>(`/${razao_social}/pedidos`);
    }

    getValor(id:number) {
        return this.http.get<number>(`/vendas/${id}`);
    }

    getVendedor(id:number) {
        return this.http.get<Pessoa>(`/venda/vendedor/${id}`);
    }

    getCliente(id:number) {
        return this.http.get<Pessoa>(`/venda/cliente/${id}`);
    }

    getVenda(id:number) {
      return this.http.get<Venda>(`/venda/${id}`);
    }
    setVenda(venda: Venda){
      console.log(venda);
      return venda;
    }

    finalizar(id:number){
      console.log(id);
      this.http.post(`/venda/finalizar`, id).subscribe(
        sucesso=> {console.log(sucesso);},
       erro => {
        console.log(erro);
      })
    }

    add(venda:Venda){
      return this.http.post<Venda>(`/venda/adicionar`, venda);
    }
    
}
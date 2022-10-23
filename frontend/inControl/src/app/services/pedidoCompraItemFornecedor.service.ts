import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, PedidoCompraItemFornecedor, User } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class PedidoCompraItemFornecedorService {
    user: User | null = null;
    constructor(
    private http: HttpClient,
    private router:Router,
    private authenticationService: AuthenticationService) {
    }

    getAll(id:number) {
        return this.http.get<PedidoCompraItemFornecedor[]>(`/pedido-compra/${id}/itens`);
    }   
    getByRazaoSocial(razao_social:string){
      return this.http.get<Loja>(`${razao_social}`);
    }
    

    add(razao_social:string, item:PedidoCompraItemFornecedor){
      this.http.post(`/PCIF/save`, item).subscribe(
            resultado => {
            },
            erro => {
              if(erro.status == 400) {
                console.log(erro);
              }
            }
        );
    }
}

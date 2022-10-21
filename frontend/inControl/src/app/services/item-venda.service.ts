import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {  User, Item, ItemVenda } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class ItemVendaService {

    user: User | null = null;
    constructor(
    private http: HttpClient,
    private router:Router,
    private authenticationService: AuthenticationService) { 
    this.authenticationService.user.subscribe(x => this.user = x);
    }

    getAll(razao_social:string, id:number) {
        return this.http.get<ItemVenda[]>(`/${razao_social}/vendas/${id}/itens`);
    }
    add(razao_social:string,item:ItemVenda){
        this.http.post(`/${razao_social}/vendas/${item.idVenda}/itens/adicionar`, item).subscribe(
            resultado => {
              console.log(resultado);
            },
            erro => {
              if(erro.status == 400) {
                console.log(erro);
              }
            }
          );
    }
    
}
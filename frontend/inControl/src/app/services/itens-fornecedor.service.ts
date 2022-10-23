import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {  User, Item, ItemFornecedor } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class ItemFornecedorService {

    user: User | null = null;
    constructor(
    private http: HttpClient,
    private router:Router,
    private authenticationService: AuthenticationService) { 
    this.authenticationService.user.subscribe(x => this.user = x);
    }

    getAll(razao_social:string) {
        return this.http.get<ItemFornecedor[]>(`/loja/${razao_social}/itensFornecedor`);
    }
    get(razao_social:string,id:number){
      return this.http.get<ItemFornecedor>(`/ItemFornecedor/${id}/item-fornecedor`)
    }
    add(item:ItemFornecedor){
        this.http.post(`/ItemFornecedor/save`, item).subscribe(
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
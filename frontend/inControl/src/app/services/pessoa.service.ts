import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, User, Item, Funcionario, Pessoa } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class PessoaService {

    user: User | null = null;
    constructor(
    private http: HttpClient,
    private router:Router,
    private authenticationService: AuthenticationService) { 
    this.authenticationService.user.subscribe(x => this.user = x);
    }

    getClientes(razao_social:string){
        return this.http.get<Pessoa[]>(`/${razao_social}/pessoas/clientes`)
    }
    add(razao_social:string, pessoa:Pessoa){
        this.http.post(`${razao_social}/pessoas/salvar`, pessoa).subscribe(
            resultado => {
              
            },
            erro => {
              if(erro.status == 400) {
                console.log(erro);
              }
            }
          );
    }
    delete(razao_social:string, id:number){
      this.http.delete(`${razao_social}/pessoas/${id}`).subscribe(resultado => {});
    }
    edit(razao_social:string, pessoa: Pessoa){
      this.http.put(`${razao_social}/pessoas/update`, pessoa).subscribe(resultado=> {});
    }
}
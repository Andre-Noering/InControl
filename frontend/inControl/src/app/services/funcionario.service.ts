import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, User, Item, Funcionario } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class FuncionarioService {

    user: User | null = null;
    constructor(
    private http: HttpClient,
    private router:Router,
    private authenticationService: AuthenticationService) { 
    this.authenticationService.user.subscribe(x => this.user = x);
    }

    getAll(razao_social:string) {
        return this.http.get<Funcionario[]>(`/${razao_social}/funcionarios`);
    }
    add(razao_social:string, funcionario:Funcionario){
        this.http.post(`/${razao_social}/funcionarios/adicionar`, funcionario).subscribe(
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
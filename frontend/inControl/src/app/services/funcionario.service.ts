import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, User, Item, Funcionario, Pessoa } from '../app.module';
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
        return this.http.get<Funcionario[]>(`/funcionarios//${razao_social}`);
    }

    getByLogin(id:string){
      return this.http.get<Funcionario>(`/funcionarios/funcionario/${id}`)
    }
    getById(id:number){
      return this.http.get<Funcionario>(`/funcionarios/funcionario/${id}/`)
    }

    add(razao_social:string, funcionario:Funcionario){
        this.http.post(`/funcionarios/adicionar`, funcionario).subscribe(
            resultado => {
              
            },
            erro => {
              if(erro.status == 400) {
                console.log(erro);
              }
            }
          );
    }
    delete(id:number){
      this.http.delete(`/funcionarios/delete/${id}`).pipe().subscribe(resultado => { },
        erro => {
          if(erro.status == 400) {
            console.log(erro);
          }
        }
      );;
    }
    edit(funcionario:Funcionario){
      this.http.put(`/funcionarios/atualizar`, funcionario).subscribe(resultado=> {});
    }
}
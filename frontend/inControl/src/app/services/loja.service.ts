import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, User } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { Router } from '@angular/router';


@Injectable({ providedIn: 'root' })
export class LojaService {
    user: User | null = null;
    constructor(
    private http: HttpClient,
    private router:Router,
    private authenticationService: AuthenticationService) {
    this.authenticationService.user.subscribe(x => this.user = x);
    }

    getAll(login:string) {
        return this.http.get<Loja[]>(`/${this.user!.login}/lojas`);
    }   
    getByRazaoSocial(razao_social:string){
      return this.http.get<Loja>(`${razao_social}`);
    }

    addLoja(loja:Loja){
        return this.http.post(`/adicionar`, loja).subscribe(
            resultado => {
              this.router.navigate([`/lojas`])
            },
            erro => {
              if(erro.status == 400) {
                console.log(erro);
              }
            }
        );
    }
}
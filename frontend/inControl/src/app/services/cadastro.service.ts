import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { first } from "rxjs";
import { Funcionario } from "../app.module";
import { AuthenticationService } from "../helpers/auth.service";


@Injectable({providedIn:'root'})
export class CadastroService {
    constructor(private http: HttpClient,
        private authenticationService: AuthenticationService,
        private router: Router) {}

    cadastro(funcionario: Funcionario){
        console.log(funcionario);
        this.http.post(`/funcionario/adicionar`,funcionario).subscribe(
            resultado => {
              console.log(resultado)
            },
            erro => {
              if(erro.status == 400) {
                console.log(erro);
              }
            }
          );
        this.authenticationService.login(funcionario.login, funcionario.senha).pipe(first())
        .subscribe(
            data => {
              console.log("caiu")
                this.router.navigate(['']);
            },
            error => {
                console.log(error)
            });;
        this.router.navigate(['']);
    }
}
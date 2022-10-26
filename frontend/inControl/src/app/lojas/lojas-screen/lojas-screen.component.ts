import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { first } from 'rxjs';
import { Funcionario, Loja, User } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-lojas-screen',
  templateUrl: './lojas-screen.component.html',
  styleUrls: ['./lojas-screen.component.css']
})
export class LojasScreenComponent implements OnInit {
  @Input() loja: Loja|null=null;

  ger:string = "Gerente";
  func:string="Funcionario";
  lojaTrab:Loja|null = null;
  user: User | null = null;
  funcionario: Funcionario | null = null;
  lojas: Loja[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private lojaService: LojaService,
    private funcionarioService: FuncionarioService) {
    this.authenticationService.user.subscribe(x => this.user = x);
    this.lojaService.getAll(this.user!.login).pipe(first()).subscribe(lojas => {
      this.lojas = lojas;
      console.log(lojas);
  });
  this.funcionarioService.getByLogin(this.user!.login).subscribe(resultado => {this.funcionario=resultado;
    if(this.funcionario?.idLoja != null){
    	this.lojaService.getById(this.funcionario.idLoja).subscribe(resultado=>this.lojaTrab=resultado);
  }
   },erro => {
    if(erro.status == 400) {
      console.log(erro);
    }});
  
  }
  ngOnInit(): void {
    
  }


  

}

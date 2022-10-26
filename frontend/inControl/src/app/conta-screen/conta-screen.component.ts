import { Component, OnInit } from '@angular/core';
import { Funcionario, User } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { FuncionarioService } from '../services/funcionario.service';

@Component({
  selector: 'app-conta-screen',
  templateUrl: './conta-screen.component.html',
  styleUrls: ['./conta-screen.component.css']
})
export class ContaScreenComponent implements OnInit {
  editando:boolean=false;
  funcionario:Funcionario|null = null;
  user:User|null = null
  constructor(private authenticationService: AuthenticationService,
    private funcionarioService: FuncionarioService) {
    this.authenticationService.user.subscribe(x => this.user = x);
    
    this.funcionarioService.getByLogin(this.user!.login).subscribe(resultado =>  this.funcionario=resultado,erro => {
      if(erro.status == 400) {
        console.log(erro);
      }});
   }

  ngOnInit(): void {
    this.funcionarioService.getByLogin(this.user!.login).subscribe(resultado =>  this.funcionario=resultado,erro => {
      if(erro.status == 400) {
        console.log(erro);
      }});
      console.log(this.funcionario)
  }
  edit(funcionario:Funcionario){
    this.editando=true;
    this.funcionario=funcionario;
    
  }
  editado(funcionario:Funcionario){
    this.funcionario= funcionario;
    this.editando = false;
  }
}

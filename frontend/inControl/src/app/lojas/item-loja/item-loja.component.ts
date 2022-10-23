import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Funcionario, Loja, User } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-item-loja',
  templateUrl: './item-loja.component.html',
  styleUrls: ['./item-loja.component.css']
})
export class ItemLojaComponent implements OnInit {
  loja: Loja | null=null;
  user: User | null = null;
  gerente:boolean = false;
  funcionario: Funcionario|null = null;
  @Output() lojaNull = new EventEmitter<null>();
  @Output() itens = new EventEmitter<boolean>();
  @Output() funcionarios = new EventEmitter<boolean>();
  
  constructor(private funcionarioService:FuncionarioService,private lojaService: LojaService, private route: ActivatedRoute,private authenticationService: AuthenticationService,) {
    this.authenticationService.user.subscribe(x => this.user = x);
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;
        this.funcionarioService.getById(this.loja.id_funcionario).subscribe(resultado => {
        if(resultado.login==this.user?.login){
          this.gerente=true;
        } else {
          this.funcionarioService.getByLogin(this.user?.login!).subscribe(resultado => this.funcionario= resultado)
        }
        })
      },
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      });
    })
  }
  sair(){
    this.lojaNull.emit(null);
  }
  setItens(valor:boolean){
    this.itens.emit(valor);
  }
  setFuncionarios(valor:boolean){
    this.funcionarios.emit(valor);
  }
 
}

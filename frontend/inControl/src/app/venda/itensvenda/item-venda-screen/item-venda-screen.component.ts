import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Funcionario, ItemVenda, Loja, User, Venda } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { ItemVendaService } from 'src/app/services/item-venda.service';
import { LojaService } from 'src/app/services/loja.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-item-venda-screen',
  templateUrl: './item-venda-screen.component.html',
  styleUrls: ['./item-venda-screen.component.css']
})
export class ItemVendaScreenComponent{
  venda!: Venda;
  user:User|null=null;
  gerente:boolean = true;
  funcionario :Funcionario|null=null;
  itensVenda : ItemVenda[] = [];
  loja:Loja|null = null;
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private itemVendaService:ItemVendaService,
    private lojaService: LojaService,
    private vendaService: VendaService,
    private route: ActivatedRoute,
    private funcionarioService:FuncionarioService,
    private router:Router) {
      this.authenticationService.user.subscribe(x => this.user = x);
      this.route.params.subscribe(params => {this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {this.loja = resultado;
      
        this.vendaService.getVenda(params['id']).subscribe(resultado => {
          this.venda=resultado;
          this.funcionarioService.getById(this.loja?.id_funcionario!).subscribe(resultado => {
            if(resultado.login==this.user?.login){
              this.gerente=true;
            } else {
              this.gerente=false;
              this.funcionarioService.getByLogin(this.user?.login!).subscribe(resultado => this.funcionario= resultado)
            };
            this.itemVendaService.getAll(this.loja?.razao_social ?? '',this.venda?.id ?? 0).pipe().subscribe(vendas => {
              this.itensVenda = vendas;
          });
            },
            erro => {
              if(erro.status == 400) {
                console.log(erro);
              }
            })})
          
      
      }, 
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      });
      }, 
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      }
    
    )}
    ;
    
      ativFinalizar(){
        if(confirm("Tem certeza que deseja finalizar a compra? Não será possível alterá-la depois.")){
          this.vendaService.finalizar(this.venda.id!);
          if(this.gerente){
          this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/vendas`])
          } else {
            this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/vendas/${this.funcionario?.idPessoa}`])
          }
        }
      }
}

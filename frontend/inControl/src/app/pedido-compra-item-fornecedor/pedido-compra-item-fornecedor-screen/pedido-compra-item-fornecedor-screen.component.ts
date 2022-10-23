import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Funcionario, Loja, PedidoCompra, PedidoCompraItemFornecedor, User } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { ItemVendaService } from 'src/app/services/item-venda.service';
import { LojaService } from 'src/app/services/loja.service';
import { PedidoCompraService } from 'src/app/services/pedido-compra.service';
import { PedidoCompraItemFornecedorService } from 'src/app/services/pedidoCompraItemFornecedor.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-pedido-compra-item-fornecedor-screen',
  templateUrl: './pedido-compra-item-fornecedor-screen.component.html',
  styleUrls: ['./pedido-compra-item-fornecedor-screen.component.css']
})
export class PedidoCompraItemFornecedorScreenComponent{
  loja: Loja|null=null;
  user:User|null=null;
  gerente:boolean = true;
  funcionario :Funcionario|null=null;
  pedidoCompra : PedidoCompra | null = null
  pedidoCompraItens : PedidoCompraItemFornecedor[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private itemVendaService:ItemVendaService,
    private lojaService: LojaService,
    private funcionarioService:FuncionarioService,
    private pedidoCompraService: PedidoCompraService,
    private pedidoCompraItemFornecedorService: PedidoCompraItemFornecedorService,
    private route: ActivatedRoute,
    private router:Router) {
      this.authenticationService.user.subscribe(x => this.user = x);
      this.route.params.subscribe(params => {this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {this.loja = resultado;
        this.pedidoCompraService.getPedido(params['id']).subscribe(resultado => {
          this.pedidoCompra=resultado;
          this.funcionarioService.getById(this.loja?.id_funcionario!).subscribe(resultado => {
            if(resultado.login==this.user?.login){
              this.gerente=true;
            } else {
              this.gerente=false;
              this.funcionarioService.getByLogin(this.user?.login!).subscribe(resultado => this.funcionario= resultado)
            };
          this.pedidoCompraItemFornecedorService.getAll(this.pedidoCompra?.id!).pipe().subscribe(itens => {
            this.pedidoCompraItens = itens;
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
        if(confirm("Tem certeza que deseja finalizar o pedido? Não será possível alterá-lo depois.")){
          this.pedidoCompraService.finalizar(this.pedidoCompra?.id!);
          if(this.gerente){
          this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/pedidosCompra`])
          } else {
            this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/pedidosCompra/${this.funcionario?.idPessoa}`])

          }
        }
      }
}

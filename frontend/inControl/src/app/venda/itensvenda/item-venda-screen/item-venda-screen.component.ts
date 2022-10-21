import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemVenda, Loja, Venda } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
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
  itensVenda : ItemVenda[] = [];
  loja:Loja|null = null;
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private itemVendaService:ItemVendaService,
    private lojaService: LojaService,
    private vendaService: VendaService,
    private route: ActivatedRoute,
    private router:Router) {
      this.route.params.subscribe(params => {this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {this.loja = resultado;
      
        this.vendaService.getVenda(params['id']).subscribe(resultado => {
          this.venda=resultado;
          this.itemVendaService.getAll(this.loja?.razao_social ?? '',this.venda?.id ?? 0).pipe().subscribe(vendas => {
            this.itensVenda = vendas;
        });
      
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
      )
    }, 
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      }
    )};
    
      ativFinalizar(){
        if(confirm("Tem certeza que deseja finalizar a compra? Não será possível alterá-la depois.")){
          this.vendaService.finalizar(this.venda.id!);
          this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/vendas`])
        }
      }
}

import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ItemFornecedor, Loja } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { ItemService } from 'src/app/services/item.service';
import { ItemFornecedorService } from 'src/app/services/itens-fornecedor.service';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-item-fornecedor-screen',
  templateUrl: './item-fornecedor-screen.component.html',
  styleUrls: ['./item-fornecedor-screen.component.css']
})
export class ItemFornecedorScreenComponent{
  loja:Loja|null=null;
  itensFornecedor:ItemFornecedor[] = [];

  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private itemService:ItemService,
    private itemFornecedorService:ItemFornecedorService,
    private lojaService: LojaService, private route: ActivatedRoute) {
      this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;
        this.itemFornecedorService.getAll(this.loja!.razao_social).pipe().subscribe(itensFornecedor => {
          this.itensFornecedor = itensFornecedor;
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
      }));
   }

}

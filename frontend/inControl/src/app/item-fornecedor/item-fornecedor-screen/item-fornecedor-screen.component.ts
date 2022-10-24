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
export class ItemFornecedorScreenComponent implements OnInit{
  loja:Loja|null=null;
  editando:boolean=false;
  itemFornecedor: ItemFornecedor|null = null;
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
  ngOnInit(): void {
    
  }
   delete(itemFornecedor:ItemFornecedor){
    this.itemFornecedorService.delete(itemFornecedor.id!);
    this.itensFornecedor = this.itensFornecedor.filter(item=> item!=itemFornecedor);
   }
   edit(itemFornecedor:ItemFornecedor){
    this.editando=true;
    this.itemFornecedor=itemFornecedor;
    
  }
  editado(itemFornecedor:ItemFornecedor){
    this.itemFornecedor= itemFornecedor;
    this.editando = false;
    this.itensFornecedor = this.itensFornecedor.map(itemFornecedorL=> {
      if(itemFornecedorL.id==itemFornecedor.id){
        return itemFornecedor;
      }
      return itemFornecedorL;
    })
  }
} 

import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, User, Item } from '../../app.module';
import { AuthenticationService } from '../../helpers/auth.service';
import { ItemService } from 'src/app/services/item.service';
import { LojaService } from 'src/app/services/loja.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-item-screen',
  templateUrl: './item-screen.component.html',
  styleUrls: ['./item-screen.component.css']
})
export class ItemScreenComponent {
  loja: Loja | null = null;
  editando:boolean=false;
  item:Item|null=null;
  user: User | null = null;
  itens: Item[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private itemService:ItemService,
    private lojaService: LojaService, private route: ActivatedRoute) {
      this.authenticationService.user.subscribe(x => this.user = x);
      this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;
        this.itemService.getAll(this.loja!.razao_social).pipe().subscribe(itens => {
          this.itens = itens;
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

   delete(item:Item){
    this.itemService.delete(this.loja?.razao_social!,item.id);
    this.itens =  this.itens.filter(itemLista => itemLista!=item);
  }
  edit(item:Item){
    this.editando=true;
    this.item=item;
    
  }
  editado(item:Item){
    this.item= item;
    this.editando = false;
    this.itens = this.itens.map(itemL=> {
      if(itemL.id==item.id){
        return item;
      }
      return itemL;
    })
  }
}

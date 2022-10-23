import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Loja, User, Venda } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { LojaService } from 'src/app/services/loja.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-venda-screen',
  templateUrl: './venda-screen.component.html',
  styleUrls: ['./venda-screen.component.css']
})
export class VendaScreenComponent{

  loja: Loja |null=null;

  user: User | null = null;
  
  vendas: Venda[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private vendaService:VendaService,
    private lojaService: LojaService,
    private route: ActivatedRoute) {
      this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;
        this.vendaService.getAll(resultado.razao_social).pipe().subscribe(vendas => {
          this.vendas = vendas;
        });
      },
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      }));
   }
}


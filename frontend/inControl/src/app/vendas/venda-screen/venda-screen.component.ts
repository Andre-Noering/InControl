<<<<<<< HEAD
import { Component, OnInit } from '@angular/core';
=======
import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Loja, User, Venda } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { LojaService } from 'src/app/services/loja.service';
import { VendaService } from 'src/app/services/venda.service';
>>>>>>> bed69b0c76019916a6b311173dde8d437d29ee95

@Component({
  selector: 'app-venda-screen',
  templateUrl: './venda-screen.component.html',
  styleUrls: ['./venda-screen.component.css']
})
<<<<<<< HEAD
export class VendaScreenComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
=======
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
          console.log(this.vendas);
        });
      },
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      }));
   }
}

>>>>>>> bed69b0c76019916a6b311173dde8d437d29ee95

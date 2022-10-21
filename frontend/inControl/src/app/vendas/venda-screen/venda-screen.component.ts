import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { Loja, User, Venda } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-venda-screen',
  templateUrl: './venda-screen.component.html',
  styleUrls: ['./venda-screen.component.css']
})
export class VendaScreenComponent implements OnInit {

  @Input() loja: Loja |null=null;
  @Input() adic: boolean = false;
  @Output() adicionandoVenda = new EventEmitter<boolean>();
  @Output() voltarVenda= new EventEmitter<boolean>();

  user: User | null = null;
  vendas: Venda[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private vendaService:VendaService) {
    this.authenticationService.user.subscribe(x => this.user = x);
   }
  ngOnInit(): void {
    this.vendaService.getAll("teste").pipe().subscribe(vendas => {
      this.vendas = vendas;
      console.log(vendas);
    });}
}


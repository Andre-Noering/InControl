import { Component, Input, OnInit } from '@angular/core';
import { Funcionario, Pessoa, Venda } from 'src/app/app.module';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-venda-list-item',
  templateUrl: './venda-list-item.component.html',
  styleUrls: ['./venda-list-item.component.css']
})
export class VendaListItemComponent implements OnInit {

  @Input() venda: Venda | null = null;
  @Input() gerente:boolean=true;
  constructor(
    public vendaService:VendaService
  ) {
    
  }

  ngOnInit(): void {
    
      }
}


<<<<<<< HEAD
import { Component, OnInit } from '@angular/core';
=======
import { Component, Input, OnInit } from '@angular/core';
import { Funcionario, Pessoa, Venda } from 'src/app/app.module';
import { VendaService } from 'src/app/services/venda.service';
>>>>>>> bed69b0c76019916a6b311173dde8d437d29ee95

@Component({
  selector: 'app-venda-list-item',
  templateUrl: './venda-list-item.component.html',
  styleUrls: ['./venda-list-item.component.css']
})
export class VendaListItemComponent implements OnInit {

<<<<<<< HEAD
  constructor() { }

  ngOnInit(): void {
  }

}
=======
  @Input() venda: Venda | null = null;
  
  constructor(
    public vendaService:VendaService
  ) {
    
  }

  ngOnInit(): void {
    
      }
}

>>>>>>> bed69b0c76019916a6b311173dde8d437d29ee95

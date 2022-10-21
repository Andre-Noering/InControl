import { Component, OnInit } from '@angular/core';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-venda-list-item',
  templateUrl: './venda-list-item.component.html',
  styleUrls: ['./venda-list-item.component.css']
})
export class VendaListItemComponent implements OnInit {

  constructor(
    private vendaService:VendaService
  ) { }

  ngOnInit(): void {
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { ItemVenda } from 'src/app/app.module';

@Component({
  selector: 'app-item-venda-list-item',
  templateUrl: './item-venda-list-item.component.html',
  styleUrls: ['./item-venda-list-item.component.css']
})
export class ItemVendaListItemComponent implements OnInit {
  @Input() itemVenda: ItemVenda|null=null;
  
  constructor() { }

  ngOnInit(): void {
  }

}

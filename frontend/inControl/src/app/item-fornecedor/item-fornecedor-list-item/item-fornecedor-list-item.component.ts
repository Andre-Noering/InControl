import { Component, Input, OnInit } from '@angular/core';
import { ItemFornecedor } from 'src/app/app.module';

@Component({
  selector: 'app-item-fornecedor-list-item',
  templateUrl: './item-fornecedor-list-item.component.html',
  styleUrls: ['./item-fornecedor-list-item.component.css']
})
export class ItemFornecedorListITemComponent implements OnInit {
  @Input() itemFornecedor: ItemFornecedor|null = null;
  constructor() { }

  ngOnInit(): void {
  }

}

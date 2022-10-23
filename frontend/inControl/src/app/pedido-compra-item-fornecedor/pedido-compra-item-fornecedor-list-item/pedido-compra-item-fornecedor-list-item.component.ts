import { Component, Input, OnInit } from '@angular/core';
import { PedidoCompraItemFornecedor } from 'src/app/app.module';

@Component({
  selector: 'app-pedido-compra-item-fornecedor-list-item',
  templateUrl: './pedido-compra-item-fornecedor-list-item.component.html',
  styleUrls: ['./pedido-compra-item-fornecedor-list-item.component.css']
})
export class PedidoCompraItemFornecedorListItemComponent implements OnInit {
  @Input() pedidoCompraItem : PedidoCompraItemFornecedor|null=null;

  constructor() { }

  ngOnInit(): void {
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { PedidoCompra } from '../app.module';

@Component({
  selector: 'app-pedido-compra-list-item',
  templateUrl: './pedido-compra-list-item.component.html',
  styleUrls: ['./pedido-compra-list-item.component.css']
})
export class PedidoCompraListItemComponent implements OnInit {
  @Input() pedido:PedidoCompra|null = null;
  constructor() { }

  ngOnInit(): void {
  }

}

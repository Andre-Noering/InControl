import { Component, Input, OnInit } from '@angular/core';
import { PedidoCompra } from 'src/app/app.module';

@Component({
  selector: 'app-pedido-compra-list-item',
  templateUrl: './pedido-compra-list-item.component.html',
  styleUrls: ['./pedido-compra-list-item.component.css']
})
export class PedidoCompraListItemComponent implements OnInit {
  @Input() pedido:PedidoCompra|null = null;
  @Input() gerente:boolean=true;

  constructor() { }

  ngOnInit(): void {
  }

}

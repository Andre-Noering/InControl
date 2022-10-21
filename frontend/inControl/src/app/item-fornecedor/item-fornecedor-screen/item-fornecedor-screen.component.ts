import { Component, OnInit } from '@angular/core';
import { ItemFornecedor } from 'src/app/app.module';

@Component({
  selector: 'app-item-fornecedor-screen',
  templateUrl: './item-fornecedor-screen.component.html',
  styleUrls: ['./item-fornecedor-screen.component.css']
})
export class ItemFornecedorScreenComponent implements OnInit {
  itensFornecedor:ItemFornecedor[] = [];
  
  constructor() { }

  ngOnInit(): void {
  }

}

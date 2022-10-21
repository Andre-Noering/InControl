import { Component, Input, OnInit } from '@angular/core';
import { Fornecedor } from 'src/app/app.module';

@Component({
  selector: 'app-fornecedor-list-item',
  templateUrl: './fornecedor-list-item.component.html',
  styleUrls: ['./fornecedor-list-item.component.css']
})
export class FornecedorListItemComponent implements OnInit {
  @Input() fornecedor!: Fornecedor;
  constructor() { }

  ngOnInit(): void {
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { Pessoa } from 'src/app/app.module';

@Component({
  selector: 'app-pessoas-list-item',
  templateUrl: './pessoas-list-item.component.html',
  styleUrls: ['./pessoas-list-item.component.css']
})
export class PessoasListItemComponent implements OnInit {
  @Input() pessoa: Pessoa|null = null;
  constructor() { }

  ngOnInit(): void {
  }

}

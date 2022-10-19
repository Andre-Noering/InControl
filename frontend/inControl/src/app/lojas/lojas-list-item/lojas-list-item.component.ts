import { Component, Input, OnInit } from '@angular/core';
import { Loja } from 'src/app/app.module';

@Component({
  selector: 'app-lojas-list-item',
  templateUrl: './lojas-list-item.component.html',
  styleUrls: ['./lojas-list-item.component.css']
})
export class LojasListItemComponent implements OnInit {

  @Input() loja!:Loja;
  
  constructor() { }

  ngOnInit(): void {
  }

}

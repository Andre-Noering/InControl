import { Component, Input, OnInit } from '@angular/core';
import { Item } from 'src/app/app.module';

@Component({
  selector: 'app-item-list-item',
  templateUrl: './item-list-item.component.html',
  styleUrls: ['./item-list-item.component.css']
})
export class ItemListItemComponent implements OnInit {

  @Input() item!:Item;

  
  constructor() { }

  ngOnInit(): void {
  }

}

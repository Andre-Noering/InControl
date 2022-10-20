import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Loja } from 'src/app/app.module';

@Component({
  selector: 'app-loja-item',
  templateUrl: './loja-item.component.html',
  styleUrls: ['./loja-item.component.css']
})
export class LojaItemComponent implements OnInit {
  @Input() loja!:Loja|null;
  @Output() lojaNull = new EventEmitter<null>;
  constructor() { }

  ngOnInit(): void {
  }

  voltar(){
    this.lojaNull.emit(null);
  }
}

import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, User, Item } from '../../app.module';
import { AuthenticationService } from '../../helpers/auth.service';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-item-screen',
  templateUrl: './item-screen.component.html',
  styleUrls: ['./item-screen.component.css']
})
export class ItemScreenComponent implements OnInit {
  @Input() loja!: Loja;
  @Output() adicionandoItem = new EventEmitter<boolean>();
  @Output() voltarItem = new EventEmitter<boolean>();

  user: User | null = null;
  itens: Item[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private itemService:ItemService) {
    this.authenticationService.user.subscribe(x => this.user = x);
   }

  ngOnInit(): void {
    this.itemService.getAll(this.loja.razao_social).pipe().subscribe(itens => {
      console.log(this.loja);
      this.itens = itens;
      
  });
  }
  addItem(){
    this.adicionandoItem.emit(true);
  }
  voltarTela(){
    console.log(this.loja);
    this.voltarItem.emit(false);
  }
}

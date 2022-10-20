import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, User, Item } from '../../app.module';
import { AuthenticationService } from '../../helpers/auth.service';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-item-screen',
  templateUrl: './item-screen.component.html',
  styleUrls: ['./item-screen.component.css']
})
export class ItemScreenComponent implements OnInit, OnChanges {
  @Input() loja: Loja |null=null;
  @Output() adicionandoItem = new EventEmitter<boolean>();
  @Output() voltarItem = new EventEmitter<boolean>();

  user: User | null = null;
  itens: Item[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private itemService:ItemService) {
    this.authenticationService.user.subscribe(x => this.user = x);
   }
  ngOnChanges(changes: SimpleChanges): void {
    this.loja = changes['loja'].currentValue;
    this.loadEstoque();
  }

  ngOnInit(): void {
    this.loadEstoque();
  }

  loadEstoque = () => {
    if (this.loja != null) {
      console.log("loja");
      this.itemService.getAll(this.loja.id).pipe().subscribe(itens => {
        this.itens = itens;
      });
    }
  }

  addItem(){
    this.adicionandoItem.emit(true);
  }
  voltarTela(){
    this.voltarItem.emit(false);
  }
}

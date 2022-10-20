import { Component, OnInit } from '@angular/core';
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

  user: User | null = null;
  itens: Item[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private itemService:ItemService) {
    this.authenticationService.user.subscribe(x => this.user = x);
   }

  ngOnInit(): void {
    this.itemService.getAll().pipe().subscribe(itens => {
      this.itens = itens;
      console.log(itens);
  });
  }

}

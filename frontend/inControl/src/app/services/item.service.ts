import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, User, Item } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';


@Injectable({ providedIn: 'root' })
export class ItemService {

    user: User | null = null;
    constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService) { 
    this.authenticationService.user.subscribe(x => this.user = x);
    }

    getAll() {
        return this.http.get<Item[]>(`/teste/itens`);
    }
}
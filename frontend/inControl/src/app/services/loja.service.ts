import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja, User } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';


@Injectable({ providedIn: 'root' })
export class LojaService {

    user: User | null = null;
    constructor(
    private http: HttpClient,
    private authenticationService: AuthenticationService) { 
    this.authenticationService.user.subscribe(x => this.user = x);
    }

    getAll(login:string) {
        return this.http.get<Loja[]>(`/${this.user!.login}/lojas`);
    }
}
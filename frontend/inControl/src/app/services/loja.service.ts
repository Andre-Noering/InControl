import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Loja } from '../app.module';


@Injectable({ providedIn: 'root' })
export class LojaService {
    constructor(private http: HttpClient) { }

    getAll(login:string) {
        return this.http.get<Loja[]>(`/{login}/lojas`);
    }
}
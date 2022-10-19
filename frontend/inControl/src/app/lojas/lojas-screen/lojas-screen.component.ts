import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs';
import { Loja, User } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-lojas-screen',
  templateUrl: './lojas-screen.component.html',
  styleUrls: ['./lojas-screen.component.css']
})
export class LojasScreenComponent implements OnInit {

  user: User | null = null;
  lojas: Loja[] = [];
  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService,
    private lojaService: LojaService) {
    this.authenticationService.user.subscribe(x => this.user = x);
   }

  ngOnInit(): void {
    this.lojaService.getAll(this.user!.login).pipe(first()).subscribe(lojas => {
      this.lojas = lojas;
  });
  }

  
}

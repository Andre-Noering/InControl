import { Component, OnInit } from '@angular/core';
import { User } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {

  user: User | null = null;
  constructor(
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.user.subscribe(x => this.user = x);
}
  
  ngOnInit(): void {
  }

}

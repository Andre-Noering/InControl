import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../helpers/auth.service';
import { first } from 'rxjs';


@Component({
  selector: 'app-login-screen',
  templateUrl: './login-screen.component.html',
  styleUrls: ['./login-screen.component.css']
})
export class LoginScreenComponent implements OnInit {

    formLogin: FormGroup = this.formBuilder.group({
        username:['', Validators.required],
        password:['',Validators.required]
    })
  constructor(
    private formBuilder:FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
    ) { 
        if (this.authenticationService.userValue) { 
            this.router.navigate(['']);
        }
  }
  login(){
    this.authenticationService.login(this.formLogin.get('username')?.value, this.formLogin.get('password')?.value).pipe(first())
    .subscribe(
        data => {
          console.log("caiu")
            this.router.navigate(['']);
        },
        error => {
            console.log(error)
        });
  }
  ngOnInit(): void {
  }

}

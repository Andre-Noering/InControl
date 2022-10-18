import { Component, OnInit } from '@angular/core';

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

  ngOnInit(): void {
  }

}
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../app.module';import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../helpers/auth.service';


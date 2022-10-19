import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Funcionario } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { CadastroService } from '../services/cadastro.service';

@Component({
  selector: 'app-cadastro-screen',
  templateUrl: './cadastro-screen.component.html',
  styleUrls: ['./cadastro-screen.component.css']
})
export class CadastroScreenComponent implements OnInit {

  formCadastro = this.formBuilder.group({
    nome:['', Validators.required],
    sobrenome:['', Validators.required],
    cpf:['', Validators.required],
    telefone:['', Validators.required],
    login:['', Validators.required],
    senha:['', Validators.required]
  });

  constructor(
    private formBuilder:FormBuilder,
    private http: HttpClient,
    private authenticationService: AuthenticationService,
    private cadastroService: CadastroService
  ) { }

  ngOnInit(): void {
  }

  cadastro(){
    this.cadastroService.cadastro(this.formCadastro.value as Funcionario);
  }
}

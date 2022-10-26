import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Funcionario, Gerente } from '../app.module';
import { AuthenticationService } from '../helpers/auth.service';
import { CadastroService } from '../services/cadastro.service';

@Component({
  selector: 'app-cadastro-screen',
  templateUrl: './cadastro-screen.component.html',
  styleUrls: ['./cadastro-screen.component.css']
})
export class CadastroScreenComponent implements OnInit {
  @Input() funcionario:Funcionario|null = null;
  @Output() editado= new EventEmitter<Funcionario>();

  formCadastro = this.formBuilder.group({
    idPessoa:[0],
    nome:['', Validators.required],
    sobrenome:['', Validators.required],
    cpf:['', Validators.required],
    telefone:['', Validators.required],
    login:['', Validators.required],
    senha:['', Validators.required],
    idLoja:[0]
  });

  constructor(
    private formBuilder:FormBuilder,
    private http: HttpClient,
    private authenticationService: AuthenticationService,
    private cadastroService: CadastroService
  ) { }

  ngOnInit(): void {
    if(this.funcionario!=null){
      this.formCadastro.patchValue(this.funcionario);
    }
  }

  cadastro(){
    if(this.funcionario==null){
    this.cadastroService.cadastro(this.formCadastro.value as Gerente);
    } else {
      this.cadastroService.edit(this.formCadastro.value as Gerente)
      this.editado.emit(this.formCadastro.value as Funcionario);
    }
  }
}

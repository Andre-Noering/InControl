import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Loja, User } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { CadastroService } from 'src/app/services/cadastro.service';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-add-loja',
  templateUrl: './add-loja.component.html',
  styleUrls: ['./add-loja.component.css']
})
export class AddLojaComponent implements OnInit {
  formLoja = this.formBuilder.group({
    razao_social:['', Validators.required],
    cnpj:['', Validators.required],
    contato:['', Validators.required],
    valor_caixa:[0, Validators.required],
    id_funcionario:[0, Validators.required]
  });

  constructor(
    private formBuilder:FormBuilder,
    private http: HttpClient,
    private lojaService: LojaService,
    private authenticationService: AuthenticationService,
    private cadastroService: CadastroService
  ) { 
    
  }

  ngOnInit(): void {
    console.log(this.authenticationService.userValue)
    this.formLoja.get('id_funcionario')?.patchValue(this.authenticationService.userValue.idPessoa);
  }

  addLoja(){
    this.lojaService.addLoja(this.formLoja.value as Loja)
  }
}

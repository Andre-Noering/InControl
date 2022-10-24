import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Funcionario, Gerente, Loja } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { CadastroService } from 'src/app/services/cadastro.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';
@Component({
  selector: 'app-add-funcionario',
  templateUrl: './add-funcionario.component.html',
  styleUrls: ['./add-funcionario.component.css']
})
export class AddFuncionarioComponent implements OnInit {
  @Input() funcionario: Funcionario|null = null;
  @Output() editado= new EventEmitter<Funcionario>();

  loja : Loja |null = null;
  
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
    private route: ActivatedRoute,
    private lojaService : LojaService,
    private funcionarioService: FuncionarioService,
    private router: Router,
  ) {
    this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
    this.loja = resultado;
    this.formCadastro.get('idLoja')?.patchValue(resultado.id)}, 
    erro => {
      if(erro.status == 400) {
        console.log(erro);
      }
    })); }

  ngOnInit(): void {
    if(this.funcionario!=null){
      this.formCadastro.patchValue(this.funcionario);
    }
  } 

  adicionarFuncionario(){
    if(this.funcionario==null){
    this.funcionarioService.add(this.loja!.razao_social,this.formCadastro.value as Funcionario);
    this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/funcionarios`])
    } else {
      this.funcionarioService.edit(this.formCadastro.value as Funcionario);
      this.editado.emit(this.formCadastro.value as Funcionario);
    }
  }
}

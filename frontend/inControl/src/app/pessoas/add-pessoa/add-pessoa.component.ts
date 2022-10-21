import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Loja, Pessoa } from 'src/app/app.module';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-add-pessoa',
  templateUrl: './add-pessoa.component.html',
  styleUrls: ['./add-pessoa.component.css']
})
export class AddPessoaComponent implements OnInit {

  loja : Loja |null = null;
  
  formCadastro = this.formBuilder.group({
    nome:['', Validators.required],
    sobrenome:['', Validators.required],
    cpf:['', Validators.required],
    telefone:['', Validators.required],
    idLoja:[0]
  });

  constructor(
    private formBuilder:FormBuilder,
    private route: ActivatedRoute,
    private lojaService : LojaService,
    private pessoaService: PessoaService,
    private router: Router,
  ) {
    this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
    console.log(resultado);
    this.loja = resultado;
    this.formCadastro.get('idLoja')?.patchValue(resultado.id)}, 
    erro => {
      if(erro.status == 400) {
        console.log(erro);
      }
    })); }

  ngOnInit(): void {
  }

  adicionarPessoa(){
    this.pessoaService.add(this.loja!.razao_social,this.formCadastro.value as Pessoa);
    this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/pessoas`])
  }
}

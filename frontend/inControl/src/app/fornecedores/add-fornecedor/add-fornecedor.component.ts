import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Fornecedor, Loja } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { CadastroService } from 'src/app/services/cadastro.service';
import { FornecedorService } from 'src/app/services/fornecedor.service';
import { LojaService } from 'src/app/services/loja.service';

@Component({
  selector: 'app-add-fornecedor',
  templateUrl: './add-fornecedor.component.html',
  styleUrls: ['./add-fornecedor.component.css']
})
export class AddFornecedorComponent implements OnInit {
  @Input() fornecedor : Fornecedor|null=null;
  @Output() editado= new EventEmitter<Fornecedor>();

  loja: Loja|null=null;
  
  formFornecedor = this.formBuilder.group({
    id:[0],
    razao_social:['', Validators.required],
    cnpj:['', Validators.required],
    contato:['', Validators.required],
    idLoja:[0, Validators.required]
  });

  constructor(
    private fornecedorService: FornecedorService,
    private formBuilder:FormBuilder,
    private http: HttpClient,
    private lojaService: LojaService,
    private authenticationService: AuthenticationService,
    private cadastroService: CadastroService,
    private route: ActivatedRoute,
    private router: Router
  ) { 
    this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
      this.loja = resultado;
      this.formFornecedor.get('idLoja')?.patchValue(resultado.id)}, 
      erro => {
        if(erro.status == 400) {
          console.log(erro);
        }
      })); 
  }

  ngOnInit(): void {
    if(this.fornecedor!=null){
      this.formFornecedor.patchValue(this.fornecedor)
    }
  }

  addFornecedor(){
    if(this.fornecedor==null){
    this.fornecedorService.add(this.loja!.razao_social,this.formFornecedor.value as Fornecedor);
    this.router.navigate([`/lojas/${this.loja?.razao_social}/fornecedores`]);
    } else {
      this.fornecedorService.edit(this.loja?.razao_social!, this.formFornecedor.value as Fornecedor);
      this.editado.emit(this.formFornecedor.value as Fornecedor);
    }
  }
}

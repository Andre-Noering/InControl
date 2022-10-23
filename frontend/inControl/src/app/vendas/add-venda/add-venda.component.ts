import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Funcionario, Loja, Pessoa, Venda } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';
import { PessoaService } from 'src/app/services/pessoa.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-add-venda',
  templateUrl: './add-venda.component.html',
  styleUrls: ['./add-venda.component.css']
})
export class AddVendaComponent implements OnInit {
  @Input()   funcionario:Funcionario|null = null;
  @Input()   gerente:boolean=true;
  
  funcEscolhido:boolean=false;
  escolhendoFunc:boolean=false;
  clienteEscolhido:boolean=false;
  escolhendoCliente:boolean=false;
  cliente:Pessoa|null = null;
  venda!:Venda|null;
  loja:Loja|null = null;
  funcionarios: Funcionario[] = [];
  clientes: Pessoa[] = [];
  formVenda = this.formBuilder.group({
    id:[null],
    data:["0000-00-00T00:00:00.0000", Validators.required],
    id_vendedor:[0, Validators.required],
    id_cliente:[0,Validators.required]
  });

  constructor(private http: HttpClient,
    private formBuilder : FormBuilder,
    private authenticationService: AuthenticationService,
    private vendaService:VendaService,
    private pessoaService: PessoaService,
    private funcionarioService:FuncionarioService,
    private lojaService: LojaService,
    private route: ActivatedRoute,
    private router:Router) {
   }
  ngOnInit(): void {
    if(this.funcionario!=null){
      this.setFuncionario(this.funcionario);
    }
    this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
      this.loja = resultado;
      console.log(this.loja);
      this.pessoaService.getClientes(this.loja!.razao_social).pipe().subscribe(pessoas => this.clientes=pessoas);console.log(this.clientes);
      this.funcionarioService.getAll(this.loja!.razao_social).pipe().subscribe(funcionarios => this.funcionarios = funcionarios);
      }));
      
  }
    setVenda(venda: Venda){
      this.venda = venda;
    }

    addVenda(){
      console.log(this.formVenda.value);
      this.vendaService.add(this.formVenda.value as Venda).subscribe(resultado=>{this.setVenda(resultado)
        ;
        this.router.navigate([`/lojas/${this.loja!.razao_social}/vendas/${this.venda!.id}/itensVenda`]);
      });
    }
    setFuncionario(funcionario:Funcionario){
      this.formVenda.get('id_vendedor')?.patchValue(funcionario.idPessoa);
      this.funcionario=funcionario;
      this.funcEscolhido=true;
    }
    setCliente(cliente:Pessoa){
      this.formVenda.get('id_cliente')?.patchValue(cliente.idPessoa);
      this.cliente=cliente;
      this.clienteEscolhido=true;
    }
}

import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Funcionario, Loja, PedidoCompra, Pessoa, Venda } from 'src/app/app.module';
import { AuthenticationService } from 'src/app/helpers/auth.service';
import { FuncionarioService } from 'src/app/services/funcionario.service';
import { LojaService } from 'src/app/services/loja.service';
import { PedidoCompraService } from 'src/app/services/pedido-compra.service';

@Component({
  selector: 'app-add-pedido-compra',
  templateUrl: './add-pedido-compra.component.html',
  styleUrls: ['./add-pedido-compra.component.css']
})
export class AddPedidoCompraComponent implements OnInit {
  @Input()   gerente:boolean=true;
  @Input()   funcionario:Funcionario|null = null;

  funcEscolhido:boolean=false;
  escolhendoFunc:boolean=false;
  pedidoCompra!:PedidoCompra|null;
  loja:Loja|null = null;
  funcionarios: Funcionario[] = [];
    formPedido = this.formBuilder.group({
    id:[null],
    data:["0000-00-00T00:00:00.0000", Validators.required],
    id_funcionario:[0, Validators.required]
  });

  constructor(private http: HttpClient,
    private formBuilder : FormBuilder,
    private authenticationService: AuthenticationService,
    private funcionarioService:FuncionarioService,
    private lojaService: LojaService,
    private route: ActivatedRoute,
    private router:Router,
    private pedidoCompraService:PedidoCompraService) {
   }
  ngOnInit(): void {
    if(this.funcionario!=null){
      this.setFuncionario(this.funcionario);
    }
    this.route.params.subscribe(params => this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
      this.loja = resultado;
      this.funcionarioService.getAll(this.loja!.razao_social).pipe().subscribe(funcionarios => this.funcionarios = funcionarios);
      }));
      
  }
  setPedido(pedido: PedidoCompra){
    this.pedidoCompra = pedido;
  }
  addPedido(){
      this.pedidoCompraService.add(this.formPedido.value as PedidoCompra).pipe().subscribe(resultado => {this.setPedido(resultado);this.router.navigate([`/lojas/${this.loja!.razao_social}/pedidosCompra/${this.pedidoCompra!.id}/pedidoCompraItensFornecedor`]);});
      };

  setFuncionario(funcionario:Funcionario){
    this.formPedido.get('id_funcionario')?.patchValue(funcionario.idPessoa);
    this.funcionario=funcionario;
    this.funcEscolhido=true;
  }
  }


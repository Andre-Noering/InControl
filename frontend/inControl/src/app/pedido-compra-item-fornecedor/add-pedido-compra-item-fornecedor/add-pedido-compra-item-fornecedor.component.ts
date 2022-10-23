import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemFornecedor, Loja, PedidoCompra, PedidoCompraItemFornecedor } from 'src/app/app.module';
import { ItemVendaService } from 'src/app/services/item-venda.service';
import { ItemService } from 'src/app/services/item.service';
import { ItemFornecedorService } from 'src/app/services/itens-fornecedor.service';
import { LojaService } from 'src/app/services/loja.service';
import { PedidoCompraService } from 'src/app/services/pedido-compra.service';
import { PedidoCompraItemFornecedorService } from 'src/app/services/pedidoCompraItemFornecedor.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-add-pedido-compra-item-fornecedor',
  templateUrl: './add-pedido-compra-item-fornecedor.component.html',
  styleUrls: ['./add-pedido-compra-item-fornecedor.component.css']
})
export class AddPedidoCompraItemFornecedorComponent implements OnInit {

  escolhendoItem : boolean = false;
  itemEscolhido: boolean = false;
  loja:Loja | null = null;
  pedidoCompra: PedidoCompra | null = null;
  item: ItemFornecedor | null = null;
  itens: ItemFornecedor[] = [];

  formPedidoItem = this.formBuilder.group({
    idFornecedor:[0, Validators.required],
    idPedidoCompra:[0, Validators.required],
    valor_unitario:[0, Validators.required],
    qtde:[0, Validators.required],
  });

  constructor(
    private formBuilder:FormBuilder,
    private pedidoCompraItemFornecedorService:PedidoCompraItemFornecedorService,
    private itemFornecedorService:ItemFornecedorService,
    private lojaService:LojaService,
    private route: ActivatedRoute,
    private router: Router,
    private pedidoCompraService: PedidoCompraService,
  ) { 
    
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.route.params.subscribe(params => {
        this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;
        this.itemFornecedorService.getAll(this.loja.razao_social).subscribe(resultado=> this.itens=resultado);
        this.pedidoCompraService.getPedido(params['id']).subscribe(resultado => {
          this.pedidoCompra=resultado;
            this.formPedidoItem.get('idPedidoCompra')!.patchValue(this.pedidoCompra!.id);
          ;},erro => {
            if(erro.status == 400) {
              console.log(erro);
            }
          })})})}, erro => {
            if(erro.status == 400) {
              console.log(erro);
            }
          })}
     


  addItemPedido(){
    this.pedidoCompraItemFornecedorService.add(this.loja!.razao_social,this.formPedidoItem.value as PedidoCompraItemFornecedor);
    this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/pedidosCompra/${this.pedidoCompra?.id}/pedidoCompraItensFornecedor`])
  }
  setItem(item:ItemFornecedor){
    this.item=item;
    this.formPedidoItem.get('idFornecedor')?.patchValue(item.id);;
    this.formPedidoItem.get('valor_unitario')?.patchValue(item.valorCompra);
    this.itemEscolhido=true
  }

}

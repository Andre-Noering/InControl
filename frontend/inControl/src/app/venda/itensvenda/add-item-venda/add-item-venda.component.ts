import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Item, ItemVenda, Loja, Venda } from 'src/app/app.module';
import { ItemVendaService } from 'src/app/services/item-venda.service';
import { ItemService } from 'src/app/services/item.service';
import { LojaService } from 'src/app/services/loja.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-add-item-venda',
  templateUrl: './add-item-venda.component.html',
  styleUrls: ['./add-item-venda.component.css']
})
export class AddItemVendaComponent implements OnInit {
  loja:Loja | null = null;
  escolhendoItem : boolean = false;
  itemEscolhido: boolean = false;
  item: Item | null = null;
  venda: Venda | null = null;
  
  itens: Item[] = [];

  formItemVenda = this.formBuilder.group({
    idItem:[0, Validators.required],
    idVenda:[0, Validators.required],
    valorUnitario:[0, Validators.required],
    qtde:[0, Validators.required],
  });

  constructor(
    private formBuilder:FormBuilder,
    private itemVendaService:ItemVendaService,
    private itemService:ItemService,
    private lojaService:LojaService,
    private route: ActivatedRoute,
    private router: Router,
    private vendaService: VendaService,
  ) { 
    
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.route.params.subscribe(params => {
        this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {
        this.loja = resultado;
        this.itemService.getAll(this.loja.razao_social).subscribe(resultado=> this.itens=resultado);
        this.vendaService.getVenda(params['id']).subscribe(resultado => {
          this.venda=resultado;
            this.formItemVenda.get('idVenda')!.patchValue(this.venda!.id);
          ;},erro => {
            if(erro.status == 400) {
              console.log(erro);
            }
          })})})}, erro => {
            if(erro.status == 400) {
              console.log(erro);
            }
          })}
     


  addItemVenda(){
    if(this.formItemVenda.get('qtde')?.value! > this.item?.qtdeEstoque!){
      alert("A quantidade informada é maior que a quantidade de itens no estoque!")
    } else {
    this.itemVendaService.add(this.loja!.razao_social,this.formItemVenda.value as ItemVenda);
    this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/vendas/${this.venda?.id}/itensVenda`])
    }
  }


  setItem(item:Item){
    this.item=item;
    this.formItemVenda.get('idItem')?.patchValue(item.id);
    this.formItemVenda.get('valorUnitario')?.patchValue(item.valor)
    this.itemEscolhido=true
  }
}
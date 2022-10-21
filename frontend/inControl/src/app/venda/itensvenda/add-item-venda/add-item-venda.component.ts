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
  venda: Venda | null = null;
  item: Item | null = null;
  itens: Item[] = [];
  formItemVenda = this.formBuilder.group({
    id_item:[0, Validators.required],
    id_venda:[0, Validators.required],
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
      this.route.params.subscribe(params => {this.lojaService.getByRazaoSocial(params['razao_social']).subscribe(resultado => {this.loja = resultado; this.itemService.getAll(this.loja.razao_social).subscribe(resultado=> this.itens= resultado)});
      this.vendaService.getVenda(params['id']).subscribe(resultado => {this.venda=resultado;});
    });
  });
    this.formItemVenda.get('id_venda')?.patchValue(this.venda?.id ?? 0)
}
  addItemVenda(){
    this.itemVendaService.add(this.loja!.razao_social,this.formItemVenda.value as ItemVenda);
    this.router.navigate([`/lojas/${this.loja?.razao_social ?? ''}/estoque`])
  }

  getItem(id:number){
    return this.itemService.get(this.loja?.razao_social ?? '', id).subscribe(resultado => this.item=resultado);
  }
}